package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keys")
public class DigitalKeyController {

    private final DigitalKeyService digitalKeyService;

    public DigitalKeyController(DigitalKeyService digitalKeyService) {
        this.digitalKeyService = digitalKeyService;
    }

    @PostMapping("/generate/{bookingId}")
    public DigitalKey generateKey(@PathVariable Long bookingId) {
        return digitalKeyService.generateKey(bookingId);
    }

    @GetMapping("/active/{bookingId}")
    public DigitalKey getActiveKey(@PathVariable Long bookingId) {
        return digitalKeyService.getActiveKeyForBooking(bookingId);
    }

    @GetMapping("/guest/{guestId}")
    public List<DigitalKey> getKeysForGuest(@PathVariable Long guestId) {
        return digitalKeyService.getKeysForGuest(guestId);
    }
}
