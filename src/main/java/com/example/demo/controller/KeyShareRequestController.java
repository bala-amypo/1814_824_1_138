package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/keyshare")
public class KeyShareRequestController {

    private final KeyShareRequestService requestService;

    @Autowired
    public KeyShareRequestController(KeyShareRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<KeyShareRequest> createRequest(@RequestBody KeyShareRequest request) {
        return ResponseEntity.ok(requestService.createRequest(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyShareRequest> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.getRequestById(id));
    }

    @GetMapping
    public ResponseEntity<List<KeyShareRequest>> getAllRequests() {
        return ResponseEntity.ok(requestService.getAllRequests());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KeyShareRequest> updateRequest(@PathVariable Long id, @RequestBody KeyShareRequest request) {
        return ResponseEntity.ok(requestService.updateRequest(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return ResponseEntity.ok("Key share request deleted successfully");
    }
}
