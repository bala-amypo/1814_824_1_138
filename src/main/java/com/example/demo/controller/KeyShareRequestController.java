package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/key-share-requests")
public class KeyShareRequestController {

    private final KeyShareRequestService service;

    public KeyShareRequestController(KeyShareRequestService service) {
        this.service = service;
    }

    @PostMapping
    public KeyShareRequest create(@RequestBody KeyShareRequest request) {
        return service.createRequest(request);
    }

    @PutMapping("/{id}/status")
    public KeyShareRequest updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/{id}"55)
    public KeyShareRequest getById(@PathVariable Long id) {
        return service.getById(id);
    }
}
