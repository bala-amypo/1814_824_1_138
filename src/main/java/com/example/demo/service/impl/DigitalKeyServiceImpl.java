package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.service.DigitalKeyService;

import java.time.Instant;
import java.util.UUID;

public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository repo;

    public DigitalKeyServiceImpl(DigitalKeyRepository repo) {
        this.repo = repo;
    }

    @Override
    public DigitalKey generateKey(Long bookingId) {
        DigitalKey key = new DigitalKey();
        key.setKeyValue(UUID.randomUUID().toString());
        key.setExpiry(Instant.now().plusSeconds(3600));
        return repo.save(key);
    }
}
