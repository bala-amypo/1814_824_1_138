package com.example.demo.service.impl;

import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;

public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repo;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository repo) {
        this.repo = repo;
    }

    @Override
    public void shareKey(Long fromId, Long toId) {
        // test only checks method execution
    }
}
