package com.example.demo.controller;

import com.example.demo.model.AccessLog;
import com.example.demo.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accesslogs")
public class AccessLogController {

    private final AccessLogService logService;

    @Autowired
    public AccessLogController(AccessLogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity<AccessLog> createLog(@RequestBody AccessLog log) {
        return ResponseEntity.ok(logService.createLog(log));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccessLog> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok(logService.getLogById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccessLog>> getAllLogs() {
        return ResponseEntity.ok(logService.getAllLogs());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLog(@PathVariable Long id) {
        logService.deleteLog(id);
        return ResponseEntity.ok("Access log deleted successfully");
    }
}
