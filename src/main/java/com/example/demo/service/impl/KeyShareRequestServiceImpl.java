package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository keyShareRequestRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository keyShareRequestRepository,
                                      DigitalKeyRepository digitalKeyRepository,
                                      GuestRepository guestRepository) {
        this.keyShareRequestRepository = keyShareRequestRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest req) {
        if (req.getShareEnd().isBefore(req.getShareStart()))
            throw new IllegalArgumentException("Share end must be after start");
        if (req.getSharedBy().equals(req.getSharedWith()))
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        DigitalKey key = digitalKeyRepository.findById(req.getDigitalKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
        Guest sharedBy = guestRepository.findById(req.getSharedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
        Guest sharedWith = guestRepository.findById(req.getSharedWith().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
        req.setDigitalKey(key);
        req.setSharedBy(sharedBy);
        req.setSharedWith(sharedWith);
        return keyShareRequestRepository.save(req);
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
