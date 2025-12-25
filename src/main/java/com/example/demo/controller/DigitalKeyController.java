package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digital-keys")
public class DigitalKeyController {

    private final DigitalKeyService keyService;

    public DigitalKeyController(DigitalKeyService keyService) {
        this.keyService = keyService;
    }

    @PostMapping("/generate/{bookingId}")
    public DigitalKey generateKey(@PathVariable Long bookingId) {
        return keyService.generateKey(bookingId);
    }

    @GetMapping("/{id}")
    public DigitalKey getKey(@PathVariable Long id) {
        return keyService.getKeyById(id);
    }

    @GetMapping("/booking/{bookingId}")
    public DigitalKey getActiveKey(@PathVariable Long bookingId) {
        return keyService.getActiveKeyForBooking(bookingId);
    }

    @GetMapping("/guest/{guestId}")
    public List<DigitalKey> getKeysForGuest(@PathVariable Long guestId) {
        return keyService.getKeysForGuest(guestId);
    }
}
