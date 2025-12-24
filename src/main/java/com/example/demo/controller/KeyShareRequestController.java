package com.example.demo.controller;

import com.example.demo.service.KeyShareRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/key-share")
public class KeyShareRequestController {

    private final KeyShareRequestService keyShareService;

    public KeyShareRequestController(KeyShareRequestService keyShareService) {
        this.keyShareService = keyShareService;
    }

    @PostMapping
    public ResponseEntity<String> shareKey(
            @RequestParam Long fromGuestId,
            @RequestParam Long toGuestId) {

        keyShareService.shareKey(fromGuestId, toGuestId);
        return ResponseEntity.ok("Key shared successfully");
    }
}
