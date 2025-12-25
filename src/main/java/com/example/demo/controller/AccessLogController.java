package com.example.demo.controller;

import com.example.demo.model.AccessLog;
import com.example.demo.service.AccessLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/access-logs")
public class AccessLogController {

    private final AccessLogService logService;

    public AccessLogController(AccessLogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public AccessLog createLog(@RequestBody AccessLog log) {
        return logService.createLog(log);
    }

    @GetMapping("/key/{keyId}")
    public List<AccessLog> getLogsByKey(@PathVariable Long keyId) {
        return logService.getLogsForKey(keyId);
    }

    @GetMapping("/guest/{guestId}")
    public List<AccessLog> getLogsByGuest(@PathVariable Long guestId) {
        return logService.getLogsForGuest(guestId);
    }
}
