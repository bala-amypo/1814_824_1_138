package com.example.demo.controller;

import com.example.demo.service.AccessLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/access")
public class AccessLogController {

    private final AccessLogService accessLogService;

    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    @PostMapping("/{guestId}")
    public ResponseEntity<String> logAccess(@PathVariable Long guestId) {
        accessLogService.logAccess(guestId);
        return ResponseEntity.ok("Access logged successfully");
    }
}
