package com.btg.pactual.btg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btg.pactual.btg.models.User;
import com.btg.pactual.btg.service.interfaces.IUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
	
	private final IUserService userService;
	
	public UserController(IUserService userService) {
		this.userService = userService;
	}
	 
	 /**
     * Endpoint para registrar un nuevo usuario o actualizar.
     * @param usuario El usuario a registrar o actualizar.
     * @return ResponseEntity con un mensaje de éxito y el estado HTTP.
     */
	@PostMapping("/register-user")
	public ResponseEntity<Map<String, String>> registrarUsuario(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
	    try {
	        userService.insertUser(user);
	        log.info("Usuario registrado: {}");
	        response.put("message", "Usuario registrado exitosamente");
	        return ResponseEntity.status(HttpStatus.CREATED).body(response); 
	    } catch (Exception e) {
	        log.error("Error al registrar el usuario: {}", e.getMessage());
	        response.put("message", e.getMessage());
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(response); 
	    }
	}

	
	/**
	 * Endpoint para obtener todos los usuarios.
     * @return Lista de usuarios registrados.
    */
	@GetMapping("/get-all-users")
	public ResponseEntity<Object> getAllUsers() {
        List<User> usuarios = userService.getAllUsers();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);	
    }
	
	
	/**
     * Endpoint para obtener un usuario por su ID.
     * @param id El ID del usuario.
     * @return El usuario encontrado.
     */
    @GetMapping("/get-user-by-id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
    	User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	
	/**
     * Endpoint para eliminar un usuario por su ID.
     * @param id El ID del usuario.
     * @return El usuario eliminado.
     */
    @DeleteMapping("/delete-user/{idUser}")
    public ResponseEntity<String> deleteUser(@PathVariable String idUser) {
    	try {
			userService.deleteUser(idUser);
	        return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
		} catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);	
		}
    }
}
