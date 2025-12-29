// package com.example.demo.service.impl;

// import com.example.demo.dto.LoginRequest;
// import com.example.demo.dto.TokenResponse;
// import com.example.demo.security.JwtTokenProvider;
// import com.example.demo.service.AuthService;
// import org.springframework.security.authentication.*;
// import org.springframework.security.core.Authentication;
// import org.springframework.stereotype.Service;

// @Service
// public class AuthServiceImpl implements AuthService {

//     private final AuthenticationManager authenticationManager;
//     private final JwtTokenProvider jwtTokenProvider;

//     public AuthServiceImpl(AuthenticationManager authenticationManager,
//                            JwtTokenProvider jwtTokenProvider) {
//         this.authenticationManager = authenticationManager;
//         this.jwtTokenProvider = jwtTokenProvider;
//     }

//     @Override
//     public TokenResponse login(LoginRequest request) {

//         Authentication authentication =
//                 authenticationManager.authenticate(
//                         new UsernamePasswordAuthenticationToken(
//                                 request.getEmail(),
//                                 request.getPassword()
//                         )
//                 );

//         String token = jwtTokenProvider.generateToken(authentication);
//         return new TokenResponse(token);
//     }
// }
package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    // ✅ LOGIN
    public AuthResponse login(AuthRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // ✅ FETCH USER (needed for id + roles)
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRoles()
        );

        return new AuthResponse(token);
    }
}
