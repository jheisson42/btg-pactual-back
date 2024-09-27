package com.btg.pactual.btg.service.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.btg.pactual.btg.models.User;
import com.btg.pactual.btg.repository.UserRepository;
import com.btg.pactual.btg.service.impl.UserService;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertUser_WithSufficientBalance() {
        User user = new User();
        user.setSaldo(600000); 

        doNothing().when(userRepository).save(user);

        userService.insertUser(user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testInsertUser_InsufficientBalance() {
        User user = new User();
        user.setSaldo(400000); 

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.insertUser(user);
        });

        assertEquals("El saldo inicial debe ser al menos COP $500.000", exception.getMessage());

        // Verificar que el repositorio no se haya llamado para guardar
        verify(userRepository, times(0)).save(user);
    }

    @Test
    public void testGetAllUsers() {
        // Crear una lista de usuarios simulados
        User user1 = new User();
        User user2 = new User();
        List<User> users = List.of(user1, user2);

        // Simular la respuesta del repositorio
        when(userRepository.findAll()).thenReturn(users);

        // Ejecutar el método
        List<User> result = userService.getAllUsers();

        // Verificar el tamaño de la lista
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById_Success() {
        // Crear un usuario simulado
        User user = new User();
        user.setId("user123");

        // Simular la respuesta del repositorio
        when(userRepository.findById("user123")).thenReturn(Optional.of(user));

        // Ejecutar el método
        User result = userService.getUserById("user123");

        // Verificar que el usuario retornado sea correcto
        assertEquals("user123", result.getId());
        verify(userRepository, times(1)).findById("user123");
    }

    @Test
    public void testGetUserById_UserNotFound() {
        // Simular que el usuario no es encontrado
        when(userRepository.findById("user123")).thenReturn(Optional.empty());

        // Verificar que se lanza la excepción cuando el usuario no es encontrado
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserById("user123");
        });

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(userRepository, times(1)).findById("user123");
    }

    @Test
    public void testDeleteUser() {
        // Crear un usuario simulado
        User user = new User();
        user.setId("user123");

        // Simular la llamada al repositorio para eliminar
        doNothing().when(userRepository).delete(user);

        // Ejecutar el método
        userService.deleteUser(user);

        // Verificar que el repositorio haya llamado el método delete
        verify(userRepository, times(1)).delete(user);
    }
}
