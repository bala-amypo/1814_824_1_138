package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DigitalKey;

public interface DigitalKeyService {

    DigitalKey createKey(DigitalKey key);

    DigitalKey getKey(Long id);

    List<DigitalKey> getKeysForBooking(Long bookingId);

    void deactivateKey(Long id);
}
