package com.student.taskmanager.service;

import com.student.taskmanager.dto.UserRegistrationRequest;
import com.student.taskmanager.model.User;
import com.student.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUser_ShouldSaveUser_WhenUsernameIsUnique() {
        UserRegistrationRequest request = new UserRegistrationRequest("testuser", "pass");
        when(userRepository.existsByUsername("testuser")).thenReturn(false);
        when(passwordEncoder.encode("pass")).thenReturn("encoded");

        userService.registerUser(request);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_ShouldThrowException_WhenUsernameExists() {
        UserRegistrationRequest request = new UserRegistrationRequest("testuser", "pass");
        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(request);
        });

        assertEquals("Username already exists!", ex.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}