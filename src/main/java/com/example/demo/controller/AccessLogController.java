package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.AccessLog;
import com.example.service.AccessLogService;

@RestController
@RequestMapping("/api/access-logs")
public class AccessLogController {

    private final AccessLogService accessLogService;

    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    /**
     * Create an access log.
     * Checks key validity and sets result as SUCCESS or DENIED.
     */
    @PostMapping
    public ResponseEntity<AccessLog> createLog(@RequestBody AccessLog log) {
        AccessLog savedLog = accessLogService.createLog(log);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    /**
     * Get logs by key ID.
     */
    @GetMapping("/key/{keyId}")
    public ResponseEntity<List<AccessLog>> getLogsForKey(@PathVariable Long keyId) {
        List<AccessLog> logs = accessLogService.getLogsForKey(keyId);
        return ResponseEntity.ok(logs);
    }

    /**
     * Get logs by guest ID.
     */
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<AccessLog>> getLogsForGuest(@PathVariable Long guestId) {
        List<AccessLog> logs = accessLogService.getLogsForGuest(guestId);
        return ResponseEntity.ok(logs);
    }
}
