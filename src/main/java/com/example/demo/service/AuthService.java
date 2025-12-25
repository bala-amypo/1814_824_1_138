package com.example.demo.service;

import com.example.demo.model.AuthRequest;
import com.example.demo.model.AuthResponse;
import com.example.demo.model.Guest;

public interface AuthService {

    Guest register(Guest guest);

    AuthResponse login(AuthRequest request);
}
