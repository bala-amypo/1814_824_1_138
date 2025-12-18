package com.example.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DigitalKey;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.service.DigitalKeyService;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository digitalKeyRepository;

    public DigitalKeyServiceImpl(DigitalKeyRepository digitalKeyRepository) {
        this.digitalKeyRepository = digitalKeyRepository;
    }

    @Override
    public DigitalKey createKey(DigitalKey key) {

        // Rule 1: expiresAt >= issuedAt
        if (key.getExpiresAt().isBefore(key.getIssuedAt())) {
            throw new IllegalArgumentException("Expiry must be after issued time");
        }

        key.setActive(true);
        return digitalKeyRepository.save(key);
    }

    @Override
    public DigitalKey getKey(Long id) {
        DigitalKey key = digitalKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DigitalKey not found"));

        // Rule 2: only active keys can be shared
        if (!key.isActive()) {
            throw new IllegalStateException("Inactive keys cannot be shared");
        }

        return key;
    }

    @Override
    public List<DigitalKey> getKeysForBooking(Long bookingId) {
        return digitalKeyRepository.findByBookingId(bookingId);
    }

    @Override
    public void deactivateKey(Long id) {
        DigitalKey key = digitalKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DigitalKey not found"));

        key.setActive(false);
        digitalKeyRepository.save(key);
    }
}
