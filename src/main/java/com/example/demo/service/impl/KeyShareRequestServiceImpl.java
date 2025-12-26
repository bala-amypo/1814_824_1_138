package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.*;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository keyShareRequestRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;

    // ✅ REQUIRED BY TEST
    public KeyShareRequestServiceImpl(
            KeyShareRequestRepository keyShareRequestRepository,
            DigitalKeyRepository digitalKeyRepository,
            GuestRepository guestRepository) {

        this.keyShareRequestRepository = keyShareRequestRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {
        if (request.getShareEnd().isBefore(request.getShareStart())) {
            throw new IllegalArgumentException("Share end must be after start");
        }
        if (request.getSharedBy().getId()
                .equals(request.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }
        return keyShareRequestRepository.save(request);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return keyShareRequestRepository.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return keyShareRequestRepository.findBySharedWithId(guestId);
    }
}
