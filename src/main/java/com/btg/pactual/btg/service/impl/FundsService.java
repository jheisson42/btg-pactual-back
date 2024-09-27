package com.btg.pactual.btg.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.btg.pactual.btg.models.CurrentFunds;
import com.btg.pactual.btg.models.Funds;
import com.btg.pactual.btg.models.Transaction;
import com.btg.pactual.btg.models.User;
import com.btg.pactual.btg.repository.FundsRepository;
import com.btg.pactual.btg.repository.UserRepository;
import com.btg.pactual.btg.service.interfaces.IFundsService;
import com.btg.pactual.btg.util.SmsSender;

@Service
public class FundsService implements IFundsService {

	private final FundsRepository fundsRepository;
	private final UserRepository userRepository;
	private final SmsSender smsSender;

	public FundsService(FundsRepository fundsRepository, UserRepository userRepository,
			SmsSender smsSender) {
		this.fundsRepository = fundsRepository;
		this.userRepository = userRepository;
		this.smsSender = smsSender;
	}

	@Override
	public String manageSubscription(String usuarioId, String fondoId, String action) throws Exception {
	    // Obtener usuario y fondo
	    User user = userRepository.findById(usuarioId)
	            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
	    Funds funds = fundsRepository.findById(fondoId)
	            .orElseThrow(() -> new IllegalArgumentException("Fondo no encontrado"));

	    // Inicializar listas si son null
	    initializeUserLists(user);

	    switch (action.toUpperCase()) {
	        case "SUBSCRIBE":
	            // Verificar si el usuario ya está suscrito al fondo
	            if (isUserSubscribedToFund(user, fondoId)) {
	                throw new Exception("El usuario ya está suscrito al fondo " + funds.getNombre());
	            }

	            // Validar saldo suficiente
	            if (user.getSaldo() < funds.getMontoMinimoVinculacion()) {
	                return "No tiene saldo disponible para vincularse al fondo " + funds.getNombre();
	            }

	            // Suscribir al fondo y actualizar saldo
	            subscribeToFund(user, funds);
	            return "Suscripción exitosa al fondo " + funds.getNombre();

	        case "CANCEL":
	            // Verificar si el usuario está suscrito al fondo
	            if (!isUserSubscribedToFund(user, fondoId)) {
	                return "El usuario no está suscrito al fondo " + funds.getNombre();
	            }

	            // Cancelar la suscripción y devolver el saldo
	            cancelSubscription(user, funds);
	            return "Suscripción cancelada para el fondo " + funds.getNombre() + ". Monto devuelto: " + funds.getMontoMinimoVinculacion();

	        default:
	            throw new IllegalArgumentException("Acción no válida: " + action);
	    }
	}

	private boolean isUserSubscribedToFund(User user, String fondoId) {
	    return user.getCurrentFunds().stream().anyMatch(f -> f.getFondoId().equals(fondoId));
	}

	private void initializeUserLists(User user) {
	    if (user.getCurrentFunds() == null) {
	        user.setCurrentFunds(new ArrayList<>());
	    }
	    if (user.getHistoryTransactions() == null) {
	        user.setHistoryTransactions(new ArrayList<>());
	    }
	}

	private void subscribeToFund(User user, Funds funds) {
	    CurrentFunds currentFunds = new CurrentFunds(funds.getId(), funds.getNombre(), LocalDateTime.now(), funds.getMontoMinimoVinculacion());
	    user.getCurrentFunds().add(currentFunds);
	    user.setSaldo(user.getSaldo() - funds.getMontoMinimoVinculacion());
	    registerTransaction(user, "SUBSCRIPCION", funds);
	    userRepository.save(user);
	    smsSender.notification("Usuario " + user.getNombre() + " se ha subscito a: " + funds.getNombre(), user.getTelefono());
	}

	private void cancelSubscription(User user, Funds funds) {
	    user.getCurrentFunds().removeIf(f -> f.getFondoId().equals(funds.getId()));
	    user.setSaldo(user.getSaldo() + funds.getMontoMinimoVinculacion());
	    registerTransaction(user, "CANCEL", funds);
	    userRepository.save(user);
	    smsSender.notification("Usuario " + user.getNombre() + " se ha cancelado su subscripcion a: " + funds.getNombre(), user.getTelefono());
	}

	private void registerTransaction(User user, String tipoTransaccion, Funds funds) {
	    Transaction transaccion = new Transaction(
	            UUID.randomUUID().toString(),
	            tipoTransaccion,
	            funds.getNombre(),
	            LocalDateTime.now(),
	            funds.getMontoMinimoVinculacion()
	    );
	    user.getHistoryTransactions().add(transaccion);
	}


	@Override
	public List<Funds> getFunds() {
		return fundsRepository.findAll();
	}

}
