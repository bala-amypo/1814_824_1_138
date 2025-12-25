package com.example.demo.service;

import com.example.demo.model.KeyShareRequest;

import java.util.List;

public interface KeyShareRequestService {

    KeyShareRequest createRequest(KeyShareRequest request);

    KeyShareRequest updateStatus(Long id, String status);

    KeyShareRequest getRequestById(Long id);

    List<KeyShareRequest> getRequestsSharedBy(Long guestId);

    List<KeyShareRequest> getRequestsSharedWith(Long guestId);
}
