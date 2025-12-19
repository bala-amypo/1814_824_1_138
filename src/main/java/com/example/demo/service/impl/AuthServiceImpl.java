package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.Auth;
import com.example.demo.repository.AuthRepository;
import com.example.demo.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public Auth register(Auth auth) {

        if (authRepository.findByUsername(auth.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        return authRepository.save(auth);
    }

    @Override
    public Auth login(String username, String password) {

        Auth auth = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        if (!auth.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return auth;
    }
}
