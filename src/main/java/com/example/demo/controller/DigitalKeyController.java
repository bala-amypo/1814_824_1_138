package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/digital-keys")
public class DigitalKeyController {

    private final DigitalKeyService digitalKeyService;

    public DigitalKeyController(DigitalKeyService digitalKeyService) {
        this.digitalKeyService = digitalKeyService;
    }

    @PostMapping
    public DigitalKey create(@RequestBody DigitalKey digitalKey) {
        return digitalKeyService.createKey(digitalKey);
    }

    @GetMapping("/{keyValue}")
    public DigitalKey getByKey(@PathVariable String keyValue) {
        return digitalKeyService.getByKeyValue(keyValue);
    }

    @GetMapping("/{keyValue}/shareable")
    public boolean canShare(@PathVariable String keyValue) {
        return digitalKeyService.canShareKey(keyValue);
    }
}
