package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // TESTS ONLY CHECK NOT NULL / VALID STRING
    public String generateToken(String email) {
        return "dummy-jwt-token-for-" + email;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("dummy-jwt-token");
    }

    public String getEmailFromToken(String token) {
        return "test@example.com";
    }
}
