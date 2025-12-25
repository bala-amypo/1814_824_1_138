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
    public DigitalKey generate(@PathVariable Long bookingId) {
        return keyService.generateKey(bookingId);
    }

    @GetMapping("/booking/{bookingId}")
    public DigitalKey getActive(@PathVariable Long bookingId) {
        return keyService.getActiveKeyForBooking(bookingId);
    }

    @GetMapping("/guest/{guestId}")
    public List<DigitalKey> getForGuest(@PathVariable Long guestId) {
        return keyService.getKeysForGuest(guestId);
    }
}
