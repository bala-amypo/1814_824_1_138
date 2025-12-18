package com.example.demo.service;

import com.example.demo.model.KeyShareRequest;

public interface KeyShareRequestService {

    KeyShareRequest createRequest(KeyShareRequest request);

    KeyShareRequest updateStatus(Long requestId, String status);

    KeyShareRequest getById(Long id);
}
