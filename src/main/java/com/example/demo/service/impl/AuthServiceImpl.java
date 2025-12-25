package com.example.demo.auth.service.impl;

import com.example.demo.auth.repository.AuthRepository;
import com.example.demo.auth.security.JwtTokenProvider;
import com.example.demo.auth.service.AuthService;
import com.example.demo.model.Guest;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthRepository authRepository,
                           JwtTokenProvider jwtTokenProvider) {
        this.authRepository = authRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(String email, String password) {
        Guest guest = authRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!guest.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        if (!guest.getActive()) {
            throw new RuntimeException("Account is deactivated");
        }

        return jwtTokenProvider.generateToken(guest.getEmail(), guest.getRole());
    }

    @Override
    public String register(String email, String password) {
        if (authRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Guest guest = new Guest();
        guest.setEmail(email);
        guest.setPassword(password);
        guest.setRole("ROLE_USER");
        guest.setActive(true);

        authRepository.save(guest);

        return jwtTokenProvider.generateToken(email, guest.getRole());
    }
}
