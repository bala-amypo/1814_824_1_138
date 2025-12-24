package com.example.demo.service.impl;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;

    public GuestServiceImpl(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Guest createGuest(Guest guest) {
        if (guestRepository.existsByEmail(guest.getEmail()))
            throw new IllegalArgumentException("Email already exists");
        guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        return guestRepository.save(guest);
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existing = getGuestById(id);
        if (guest.getFullName() != null) existing.setFullName(guest.getFullName());
        if (guest.getPhoneNumber() != null) existing.setPhoneNumber(guest.getPhoneNumber());
        if (guest.getRole() != null) existing.setRole(guest.getRole());
        existing.setActive(guest.isActive());
        existing.setVerified(guest.isVerified());
        return guestRepository.save(existing);
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest existing = getGuestById(id);
        existing.setActive(false);
        guestRepository.save(existing);
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }
}
