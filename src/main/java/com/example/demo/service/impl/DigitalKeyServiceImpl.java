package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.DigitalKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    @Autowired
    private DigitalKeyRepository digitalKeyRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Override
    public DigitalKey createKey(DigitalKey key) {
        // Validate booking exists
        Long bookingId = key.getBooking().getId();
        RoomBooking booking = roomBookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        // Validate expiresAt >= issuedAt
        if (key.getExpiresAt().isBefore(key.getIssuedAt())) {
            throw new IllegalArgumentException("expiresAt must be after or equal to issuedAt");
        }

        key.setBooking(booking);
        key.setActive(true); // By default, a new key is active
        return digitalKeyRepository.save(key);
    }

    @Override
    public Optional<DigitalKey> getKeyById(Long id) {
        return digitalKeyRepository.findById(id);
    }

    @Override
    public List<DigitalKey> getActiveKeysForBooking(Long bookingId) {
        return digitalKeyRepository.findByBookingIdAndActiveTrue(bookingId);
    }

    @Override
    public void deactivateKey(Long id) {
        DigitalKey key = digitalKeyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Key not found"));
        key.setActive(false);
        digitalKeyRepository.save(key);
    }
}
