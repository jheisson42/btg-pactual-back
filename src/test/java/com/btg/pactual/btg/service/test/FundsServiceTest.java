package com.btg.pactual.btg.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.btg.pactual.btg.models.CurrentFunds;
import com.btg.pactual.btg.models.Funds;
import com.btg.pactual.btg.models.User;
import com.btg.pactual.btg.repository.FundsRepository;
import com.btg.pactual.btg.repository.UserRepository;
import com.btg.pactual.btg.service.impl.FundsService;
import com.btg.pactual.btg.util.SmsSender;

public class FundsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FundsRepository fundsRepository;

    @Mock
    private SmsSender smsSender;

    @InjectMocks
    private FundsService fundsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testManageSubscription_SuccessfulSubscription() throws Exception {
    	
        User user = new User();
        user.setId("user123");
        user.setSaldo(100000); 

        Funds fund = new Funds();
        fund.setId("fund123");
        fund.setMontoMinimoVinculacion((double) 50000); 

        when(userRepository.findById("user123")).thenReturn(Optional.of(user));
        when(fundsRepository.findById("fund123")).thenReturn(Optional.of(fund));

        String result = fundsService.manageSubscription("user123", "fund123", "SUBSCRIBE");

        assertEquals("Suscripción exitosa al fondo " + fund.getNombre(), result);

        verify(userRepository, times(1)).save(user);
        verify(smsSender, times(1)).notification(anyString(), eq(user.getTelefono()));
    }

    @Test
    public void testManageSubscription_InsufficientFunds() throws Exception {
        User user = new User();
        user.setId("user123");
        user.setSaldo(10000); 

        Funds fund = new Funds();
        fund.setId("fund123");
        fund.setMontoMinimoVinculacion((double) 50000); // Monto de suscripción

        when(userRepository.findById("user123")).thenReturn(Optional.of(user));
        when(fundsRepository.findById("fund123")).thenReturn(Optional.of(fund));

        String result = fundsService.manageSubscription("user123", "fund123", "SUBSCRIBE");

        assertEquals("No tiene saldo disponible para vincularse al fondo " + fund.getNombre(), result);

        verify(userRepository, times(0)).save(user);
    }

    @Test
    public void testManageSubscription_UserAlreadySubscribed() throws Exception {
    	
        Date now = new Date();

        User user = new User();
        user.setId("user123");
        user.setSaldo(100000);

        Funds fund = new Funds();
        fund.setId("fund123");
        fund.setMontoMinimoVinculacion((double) 50000);

        user.setCurrentFunds(List.of(new CurrentFunds(fund.getId(), fund.getNombre(),now, fund.getMontoMinimoVinculacion())));

        when(userRepository.findById("user123")).thenReturn(Optional.of(user));
        when(fundsRepository.findById("fund123")).thenReturn(Optional.of(fund));

        Exception exception = assertThrows(Exception.class, () -> {
            fundsService.manageSubscription("user123", "fund123", "SUBSCRIBE");
        });

        assertEquals("El usuario ya está suscrito al fondo " + fund.getNombre(), exception.getMessage());
    }
}
