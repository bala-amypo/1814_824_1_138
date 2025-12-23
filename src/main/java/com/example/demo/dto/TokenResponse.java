package com.example.demo.dto;

public class TokenResponse {

    private String token;
    private String tokenType = "Bearer";

    // ✅ No-args constructor
    public TokenResponse() {
    }

    
    public TokenResponse(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
