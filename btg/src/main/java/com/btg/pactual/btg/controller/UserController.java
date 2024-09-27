package com.btg.pactual.btg.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.btg.pactual.btg.models.User;
import com.btg.pactual.btg.service.interfaces.IUserService;

@CrossOrigin
@RestController
@RequestMapping("users")
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
    public ResponseEntity<String> registrarUsuario(@RequestBody User user) {
    	try {
    		userService.insertUser(user);
            return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);	
		} catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);	
		}
        
    }
	
	/**
	 * Endpoint para obtener todos los usuarios.
     * @return Lista de usuarios registrados.
    */
	@GetMapping("/get-all-user")
	public ResponseEntity<Object> getAllUsers() {
        List<User> usuarios = userService.getAllUsers();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);	
    }
	
	
	/**
     * Endpoint para obtener un usuario por su ID.
     * @param id El ID del usuario.
     * @return El usuario encontrado.
     */
    @GetMapping("/get-user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
    	User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
	
	
	/**
     * Endpoint para eliminar un usuario por su ID.
     * @param id El ID del usuario.
     * @return El usuario eliminado.
     */
    @DeleteMapping("/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.CREATED);
    }
}
