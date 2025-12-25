package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Guest;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;

import java.time.LocalDateTime;
import java.util.List;

public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repo;
    private final GuestRepository guestRepo;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository repo,
                                      GuestRepository guestRepo) {
        this.repo = repo;
        this.guestRepo = guestRepo;
    }

    @Override
    public KeyShareRequest createRequest(KeyShareRequest request) {

        if (request.getShareEnd().isBefore(request.getShareStart())) {
            throw new IllegalArgumentException("Share end must be after start");
        }

        if (request.getSharedBy().getId()
                .equals(request.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }

        Guest sharedWith = guestRepo.findById(request.getSharedWith().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        if (!sharedWith.isActive() || !sharedWith.isVerified()) {
            throw new IllegalStateException("Guest inactive or unverified");
        }

        request.setCreatedAt(LocalDateTime.now());
        return repo.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long id, String status) {
        KeyShareRequest req = getRequestById(id);
        req.setStatus(status);
        return repo.save(req);
    }

    @Override
    public KeyShareRequest getRequestById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
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
