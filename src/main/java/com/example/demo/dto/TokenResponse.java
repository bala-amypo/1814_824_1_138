package com.example.demo.dto;

public class TokenResponse {
    private String token;
    private String tokenType = "Bearer";

    public TokenResponse(String token) {
        this.token = token;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getTokenType() { return tokenType; }
    public void setTokenType(String tokenType) { this.tokenType = tokenType; }
}
