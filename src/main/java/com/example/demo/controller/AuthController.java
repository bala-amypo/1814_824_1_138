package com.example.demo.controller;

import com.example.demo.model.Guest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.GuestService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final GuestService guestService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider,
                          GuestService guestService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.guestService = guestService;
    }

    @PostMapping("/register")
    public Guest register(@RequestBody Guest guest) {
        return guestService.createGuest(guest);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(email, password)
                );

        return tokenProvider.generateToken(authentication);
    }
}
    