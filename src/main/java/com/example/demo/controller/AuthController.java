package com.example.demo.auth;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.TokenResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {

        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );

        return new TokenResponse(token);
    }
}
