package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.TokenResponseDTO;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public TokenResponseDTO login(AuthRequestDTO request) {
        // Generate a token for the given username
        String token = jwtTokenProvider.generateToken(request.getUsername());
        return new TokenResponseDTO(token);
    }
}
