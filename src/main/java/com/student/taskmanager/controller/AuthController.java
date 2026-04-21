package com.student.taskmanager.controller;

import com.student.taskmanager.dto.UserRegistrationRequest;
import com.student.taskmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // POST /auth/register → register a new user
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserRegistrationRequest request) {
        userService.registerUser(request);
        return "User registered successfully!";
    }

    // POST /auth/login → authenticate the user (session based via Spring Security)
    @PostMapping("/login")
    public String login() {
        return "Login successful!";
    }
}