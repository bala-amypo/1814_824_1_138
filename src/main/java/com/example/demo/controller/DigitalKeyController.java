package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/keys")
@Tag(name = "Digital Keys")
public class DigitalKeyController {

    private final DigitalKeyService digitalKeyService;

    public DigitalKeyController(DigitalKeyService digitalKeyService) {
        this.digitalKeyService = digitalKeyService;
    }

    @PostMapping
    public DigitalKey create(@RequestBody DigitalKey key) {
        return digitalKeyService.createKey(key);
    }

    @GetMapping("/{id}")
    public DigitalKey get(@PathVariable Long id) {
        return digitalKeyService.getKey(id);
    }

    @GetMapping("/booking/{bookingId}")
    public List<DigitalKey> listForBooking(@PathVariable Long bookingId) {
        return digitalKeyService.getKeysForBooking(bookingId);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        digitalKeyService.deactivateKey(id);
    }
}
