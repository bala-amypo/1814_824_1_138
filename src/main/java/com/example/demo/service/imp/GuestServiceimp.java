
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
        Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));

        existingGuest.setName(guest.getName());
        existingGuest.setEmail(guest.getEmail());
        existingGuest.setPhone(guest.getPhone());

        return guestRepository.save(existingGuest);
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest guest = guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));

        guest.setActive(false);
        guestRepository.save(guest);
    }
}
