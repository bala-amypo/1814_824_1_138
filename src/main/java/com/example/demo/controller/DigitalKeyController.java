package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digital-keys")
public class DigitalKeyController {

    @Autowired
    private DigitalKeyService digitalKeyService;

    // POST /generate/{bookingId} - Generate key
    @PostMapping("/generate/{bookingId}")
    public DigitalKey generateKey(@PathVariable Long bookingId) {
        return digitalKeyService.generateKey(bookingId);
    }

    // GET /{id} - Get key by key id
    @GetMapping("/{id}")
    public DigitalKey getKey(@PathVariable Long id) {
        return digitalKeyService.getKeyById(id);
    }

    // GET /active/{bookingId} - Get active key for a booking
    @GetMapping("/active/{bookingId}")
    public DigitalKey getActiveKey(@PathVariable Long bookingId) {
        return digitalKeyService.getActiveKeyForBooking(bookingId);
    }

    // GET /guest/{guestId} - List keys for a guest
    @GetMapping("/guest/{guestId}")
    public List<DigitalKey> getKeysForGuest(@PathVariable Long guestId) {
        return digitalKeyService.getKeysForGuest(guestId);
    }
}
