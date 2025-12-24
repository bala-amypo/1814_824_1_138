package com.example.demo.controller;

import com.example.demo.model.AccessLog;
import com.example.demo.service.AccessLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/access-logs")
public class AccessLogController {

    private final AccessLogService accessLogService;

    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    @PostMapping
    public AccessLog createLog(@RequestBody AccessLog log) {
        return accessLogService.createLog(log);
    }

    @GetMapping("/guest/{guestId}")
    public List<AccessLog> getLogsForGuest(@PathVariable Long guestId) {
        return accessLogService.getLogsForGuest(guestId);
    }

    @GetMapping("/key/{keyId}")
    public List<AccessLog> getLogsForKey(@PathVariable Long keyId) {
        return accessLogService.getLogsForKey(keyId);
    }
}
