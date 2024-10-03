package com.btg.pactual.btg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.btg.pactual.btg.models.User;
import com.btg.pactual.btg.repository.UserRepository;
import com.btg.pactual.btg.service.interfaces.IUserService;

@Service
public class UserService implements IUserService{
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

    /**
     * Método para registrar un nuevo usuario.
     * @param usuario El usuario a registrar.
     */
	@Override
	public void insertUser(User user) {
		 List<User> userFind = new ArrayList<>(); 
		
		// Valida que el saldo inicial sea suficiente
        if (user.getSaldo() < 500000) {
            throw new IllegalArgumentException("El saldo inicial debe ser al menos COP $500.000");
        }

		// Valida que el usuario no exista
        userFind = userRepository.findByEmail(user.getEmail());
        
        if (!userFind.isEmpty()) {
            throw new IllegalArgumentException("El correo: " + user.getEmail() + " se encuentra registrado.");
        }
        userRepository.save(user);		
	}

    /**
     * Método para obtener todos los usuarios registrados.
     * @return Una lista de usuarios.
     */
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	  /**
     * Método para obtener un usuario por su ID.
     * @param id El ID del usuario.
     * @return El usuario encontrado o lanza una excepción si no existe.
     */
	@Override
	public User getUserById(String id) {
		return userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
	}

	@Override
	public void deleteUser(String idUser) {
		 // Encuentra usuario por su ID
		User userEntity = getUserById(idUser);

	    // Si el usuario existe se elimina
	    if (userEntity != null) {
	        userRepository.delete(userEntity);
	    } else {
	        throw new IllegalArgumentException("Usuario no encontrado para el ID: " + idUser);
	    }
	 }

}
