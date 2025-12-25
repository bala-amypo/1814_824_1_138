package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-share")
public class KeyShareRequestController {

    private final KeyShareRequestService service;

    public KeyShareRequestController(KeyShareRequestService service) {
        this.service = service;
    }

    @PostMapping
    public KeyShareRequest create(@RequestBody KeyShareRequest req) {
        return service.createShareRequest(req);
    }

    @GetMapping("/shared-by/{guestId}")
    public List<KeyShareRequest> sharedBy(@PathVariable Long guestId) {
        return service.getRequestsSharedBy(guestId);
    }

    @GetMapping("/shared-with/{guestId}")
    public List<KeyShareRequest> sharedWith(@PathVariable Long guestId) {
        return service.getRequestsSharedWith(guestId);
    }
}
