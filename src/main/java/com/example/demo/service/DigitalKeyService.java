package com.example.demo.service;

import com.example.demo.model.DigitalKey;
import java.util.List;

public interface DigitalKeyService {
    DigitalKey createKey(DigitalKey key);
    DigitalKey getKeyById(Long id);
    List<DigitalKey> getAllKeys();
    DigitalKey updateKey(Long id, DigitalKey key);
    void deleteKey(Long id);
}
