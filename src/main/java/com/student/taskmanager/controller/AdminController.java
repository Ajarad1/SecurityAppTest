package com.student.taskmanager.controller;

import com.student.taskmanager.dto.UserRegistrationRequest;
import com.student.taskmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // POST /admin/users → create a new user (admin access only)
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUserByAdmin(@RequestBody UserRegistrationRequest request) {
        userService.registerUser(request);
        return "User created successfully by Admin!";
    }
}