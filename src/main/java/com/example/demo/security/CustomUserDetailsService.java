package com.example.demo.security;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Guest guest = guestRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        return UserPrincipal.create(guest);
    }

    // Optional helper method to load by ID
    public UserDetails loadUserById(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));
        return UserPrincipal.create(guest);
    }
}
