package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.*;
import com.example.demo.service.KeyShareRequestService;

import java.util.List;

public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repo;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;

    public KeyShareRequestServiceImpl(
            KeyShareRequestRepository repo,
            DigitalKeyRepository keyRepo,
            GuestRepository guestRepo) {
        this.repo = repo;
        this.keyRepo = keyRepo;
        this.guestRepo = guestRepo;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest req) {
        if (req.getShareEnd().isBefore(req.getShareStart())) {
            throw new IllegalArgumentException("Share end before start");
        }
        if (req.getSharedBy().getId().equals(req.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }
        return repo.save(req);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return repo.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return repo.findBySharedWithId(guestId);
    }
}
