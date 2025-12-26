package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
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