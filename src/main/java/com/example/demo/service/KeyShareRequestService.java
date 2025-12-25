package com.example.demo.service;

import com.example.demo.model.KeyShareRequest;
import java.util.List;

public interface KeyShareRequestService {
    KeyShareRequest createRequest(KeyShareRequest request);
    KeyShareRequest getRequestById(Long id);
    List<KeyShareRequest> getAllRequests();
    KeyShareRequest updateRequest(Long id, KeyShareRequest request);
    void deleteRequest(Long id);
}
