package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * POST /auth/register
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(authService.register(user), HttpStatus.CREATED);
    }

    /**
     * POST /auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        return ResponseEntity.ok(
                authService.login(user.getUsername(), user.getPassword())
        );
    }
}
