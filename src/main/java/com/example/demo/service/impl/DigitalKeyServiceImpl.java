package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.DigitalKeyService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service   // 🔴 THIS IS CRITICAL
public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;

    public DigitalKeyServiceImpl(
            DigitalKeyRepository digitalKeyRepository,
            GuestRepository guestRepository) {
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public DigitalKey generateKey(Long guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        DigitalKey key = new DigitalKey();
        key.setGuest(guest);
        key.setKeyValue(UUID.randomUUID().toString());
        key.setActive(true);

        return digitalKeyRepository.save(key);
    }

    @Override
    public DigitalKey getKeyByGuestId(Long guestId) {
        return digitalKeyRepository.findByGuestId(guestId)
                .orElseThrow(() -> new RuntimeException("Digital key not found"));
    }
}
