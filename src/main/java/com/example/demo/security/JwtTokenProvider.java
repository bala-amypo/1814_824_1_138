package com.example.demo.security;

import org.springframework.security.core.Authentication;

public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {
        return null;
    }

    public boolean validateToken(String token) {
        return false;
    }

    public Long getUserIdFromToken(String token) {
        return null;
    }

    public String getEmailFromToken(String token) {
        return null;
    }

    public String getRoleFromToken(String token) {
        return null;
    }
}
