package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/keys")
public class DigitalKeyController {

    private final DigitalKeyService keyService;

    @Autowired
    public DigitalKeyController(DigitalKeyService keyService) {
        this.keyService = keyService;
    }

    @PostMapping
    public ResponseEntity<DigitalKey> createKey(@RequestBody DigitalKey key) {
        return ResponseEntity.ok(keyService.createKey(key));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigitalKey> getKeyById(@PathVariable Long id) {
        return ResponseEntity.ok(keyService.getKeyById(id));
    }

    @GetMapping
    public ResponseEntity<List<DigitalKey>> getAllKeys() {
        return ResponseEntity.ok(keyService.getAllKeys());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DigitalKey> updateKey(@PathVariable Long id, @RequestBody DigitalKey key) {
        return ResponseEntity.ok(keyService.updateKey(id, key));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKey(@PathVariable Long id) {
        keyService.deleteKey(id);
        return ResponseEntity.ok("Digital key deleted successfully");
    }
}
