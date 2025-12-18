package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digital-keys")
public class DigitalKeyController {

    private final DigitalKeyService digitalKeyService;

    // Constructor Injection (recommended)
    public DigitalKeyController(DigitalKeyService digitalKeyService) {
        this.digitalKeyService = digitalKeyService;
    }

    // POST /generate/{bookingId} - Generate key
    @PostMapping("/generate/{bookingId}")
    public DigitalKey generateKey(@PathVariable Long bookingId) {
        return digitalKeyService.generateKey(bookingId);
    }

    // GET /{id} - Get key
    @GetMapping("/{id}")
    public DigitalKey getKey(@PathVariable Long id) {
        return digitalKeyService.getKeyById(id);
    }

    // GET /active/{bookingId} - Get active key
    @GetMapping("/active/{bookingId}")
    public DigitalKey getActiveKey(@PathVariable Long bookingId) {
        return digitalKeyService.getActiveKeyForBooking(bookingId);
    }

    // GET /guest/{guestId} - List keys
    @GetMapping("/guest/{guestId}")
    public List<DigitalKey> getKeysForGuest(@PathVariable Long guestId) {
        return digitalKeyService.getKeysForGuest(guestId);
    }
}
