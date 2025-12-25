package com.example.demo.dto;

import java.time.Instant;

public class TokenResponse {

    private String token;
    private String email;
    private String role;
    private Instant issuedAt;
    private Instant expiresAt;

    // Getters and Setters

    public String getToken() {
        return token;
    }
 
    public void setToken(String token) {
        this.token = token;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getRole() {
        return role;
    }
 
    public void setRole(String role) {
        this.role = role;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }
 
    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }
 
    public Instant getExpiresAt() {
        return expiresAt;
    }
 
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }
}
