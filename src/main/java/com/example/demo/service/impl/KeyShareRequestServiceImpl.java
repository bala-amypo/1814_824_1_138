package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.model.KeyShareStatus;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repository;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public KeyShareRequest createRequest(KeyShareRequest request) {
        request.setStatus(KeyShareStatus.PENDING);
        return repository.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long requestId, String status) {

        KeyShareRequest request = getById(requestId);
        request.setStatus(KeyShareStatus.valueOf(status.toUpperCase()));
        return repository.save(request);
    }

    @Override
    public KeyShareRequest getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("KeyShareRequest not found"));
    }
}
