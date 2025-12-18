package com.example.demo.service;

import com.example.demo.model.DigitalKey;
import java.util.List;
import java.util.Optional;

public interface DigitalKeyService {

    DigitalKey createKey(DigitalKey key);

    Optional<DigitalKey> getKeyById(Long id);

    List<DigitalKey> getActiveKeysForBooking(Long bookingId);

    void deactivateKey(Long id);

}
