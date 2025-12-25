package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-share")
public class KeyShareRequestController {

    private final KeyShareRequestService shareService;

    public KeyShareRequestController(KeyShareRequestService shareService) {
        this.shareService = shareService;
    }

    @PostMapping
    public KeyShareRequest createRequest(@RequestBody KeyShareRequest request) {
        return shareService.createRequest(request);
    }

    @PutMapping("/{id}/status")
    public KeyShareRequest updateStatus(@PathVariable Long id,
                                        @RequestParam String status) {
        return shareService.updateStatus(id, status);
    }

    @GetMapping("/{id}")
    public KeyShareRequest getRequest(@PathVariable Long id) {
        return shareService.getRequestById(id);
    }

    @GetMapping("/shared-by/{guestId}")
    public List<KeyShareRequest> getSharedBy(@PathVariable Long guestId) {
        return shareService.getRequestsSharedBy(guestId);
    }

    @GetMapping("/shared-with/{guestId}")
    public List<KeyShareRequest> getSharedWith(@PathVariable Long guestId) {
        return shareService.getRequestsSharedWith(guestId);
    }
}
