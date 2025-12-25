package com.example.demo.auth.controller;

import com.example.demo.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        return authService.login(email, password);
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
                           @RequestParam String password) {
        return authService.register(email, password);
    }
}
