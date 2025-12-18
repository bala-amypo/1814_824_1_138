package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.service.DigitalKeyService;
import org.springframework.stereotype.Service;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository digitalKeyRepository;

    public DigitalKeyServiceImpl(DigitalKeyRepository digitalKeyRepository) {
        this.digitalKeyRepository = digitalKeyRepository;
    }

    @Override
    public DigitalKey createKey(DigitalKey digitalKey) {
        return digitalKeyRepository.save(digitalKey);
    }

    @Override
    public DigitalKey getByKeyValue(String keyValue) {
        return digitalKeyRepository.findByKeyValue(keyValue)
                .orElseThrow(() -> new RuntimeException("Digital key not found"));
    }

    @Override
    public boolean canShareKey(String keyValue) {
        DigitalKey key = getByKeyValue(keyValue);
        return Boolean.TRUE.equals(key.getActive());
    }
}
