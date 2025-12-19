package com.example.demo.service;

import com.example.demo.model.Auth;

public interface AuthService {

    Auth register(Auth auth);

    Auth login(String username, String password);
}
