package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Auth;
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
    public ResponseEntity<Auth> register(@RequestBody Auth auth) {
        return new ResponseEntity<>(authService.register(auth), HttpStatus.CREATED);
    }

    /**
     * POST /auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<Auth> login(@RequestBody Auth auth) {
        return ResponseEntity.ok(
                authService.login(auth.getUsername(), auth.getPassword())
        );
    }
}
