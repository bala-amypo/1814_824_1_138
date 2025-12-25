package com.example.demo.service;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.TokenResponseDTO;

public interface AuthService {
    TokenResponseDTO login(AuthRequestDTO request);
}
