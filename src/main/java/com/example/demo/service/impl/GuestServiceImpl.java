package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;

public class GuestServiceImpl implements GuestService {

    private final GuestRepository repo;

    public GuestServiceImpl(GuestRepository repo) {
        this.repo = repo;
    }

    @Override
    public Guest registerGuest(Guest guest) {
        return repo.save(guest);
    }

    @Override
    public Guest getGuestById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
    }
}
