package com.example.demo.service;

import com.example.demo.model.DigitalKey;

public interface DigitalKeyService {

    DigitalKey generateKey(Long guestId);

    DigitalKey getKeyByGuestId(Long guestId);
}
