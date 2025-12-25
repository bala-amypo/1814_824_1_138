package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DigitalKey;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.service.DigitalKeyService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository keyRepository;

    public DigitalKeyServiceImpl(DigitalKeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    @Override
    public DigitalKey createKey(DigitalKey key) {
        return keyRepository.save(key);
    }

    @Override
    public DigitalKey getKeyById(Long id) {
        return keyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DigitalKey not found with id " + id));
    }

    @Override
    public List<DigitalKey> getAllKeys() {
        return keyRepository.findAll();
    }

    @Override
    public DigitalKey updateKey(Long id, DigitalKey key) {
        DigitalKey existing = getKeyById(id);
        existing.setRoomBooking(key.getRoomBooking());
        existing.setKeyCode(key.getKeyCode());
        existing.setActive(key.isActive());
        return keyRepository.save(existing);
    }

    @Override
    public void deleteKey(Long id) {
        keyRepository.deleteById(id);
    }
}
