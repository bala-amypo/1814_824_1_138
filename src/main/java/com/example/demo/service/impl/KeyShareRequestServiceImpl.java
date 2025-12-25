package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository requestRepository;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public KeyShareRequest createRequest(KeyShareRequest request) {
        return requestRepository.save(request);
    }

    @Override
    public KeyShareRequest getRequestById(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("KeyShareRequest not found with id " + id));
    }

    @Override
    public List<KeyShareRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public KeyShareRequest updateRequest(Long id, KeyShareRequest request) {
        KeyShareRequest existing = getRequestById(id);
        existing.setDigitalKey(request.getDigitalKey());
        existing.setRecipient(request.getRecipient());
        existing.setStatus(request.getStatus());
        return requestRepository.save(existing);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}
