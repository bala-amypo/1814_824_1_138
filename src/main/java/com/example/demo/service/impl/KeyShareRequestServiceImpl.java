package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;

import java.util.List;

public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repo;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository r,
                                      DigitalKeyRepository k,
                                      GuestRepository g) {
        this.repo = r;
        this.keyRepo = k;
        this.guestRepo = g;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest req) {

        if (req.getShareEnd().isBefore(req.getShareStart())) {
            throw new IllegalArgumentException("Share end before start");
        }

        if (req.getSharedBy().getId().equals(req.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }

        Guest sharedWith = guestRepo.findById(req.getSharedWith().getId()).orElseThrow();
        if (!sharedWith.getVerified() || !sharedWith.getActive()) {
            throw new IllegalStateException("Guest inactive or not verified");
        }

        DigitalKey key = keyRepo.findById(req.getDigitalKey().getId()).orElseThrow();
        req.setDigitalKey(key);

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
