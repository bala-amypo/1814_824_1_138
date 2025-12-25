package com.example.demo.service;

import com.example.demo.model.DigitalKey;

public interface DigitalKeyService {

    DigitalKey generateKey(Long bookingId);

    DigitalKey getActiveKeyByBooking(Long bookingId);

    void deactivateKey(Long keyId);
}
