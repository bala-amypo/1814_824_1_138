package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Guest;
import com.example.repository.GuestRepository;
import com.example.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Guest createGuest(Guest guest) {
        if (guestRepository.existsByEmail(guest.getEmail())) {
            throw new RuntimeException("Guest already exists");
        }
        guest.setActive(true);
        return guestRepository.save(guest);
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existing = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        existing.setName(guest.getName());
        existing.setEmail(guest.getEmail());
        existing.setPhone(guest.getPhone());

        return guestRepository.save(existing);
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
        guest.setActive(false);
        guestRepository.save(guest);
    }
}
