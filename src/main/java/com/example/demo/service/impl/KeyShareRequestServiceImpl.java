package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
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
        request.setStatus(KeyShareRequest.KeyShareStatus.PENDING);
        return repository.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long id, String status) {
        KeyShareRequest request = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(
                KeyShareRequest.KeyShareStatus.valueOf(status.toUpperCase())
        );

        return repository.save(request);
    }

    @Override
    public KeyShareRequest getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
    }
}
