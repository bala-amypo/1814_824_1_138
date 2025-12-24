package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.service.AccessLogService;

import java.time.Instant;

public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository repo;

    public AccessLogServiceImpl(AccessLogRepository repo) {
        this.repo = repo;
    }

    @Override
    public void logAccess(Long guestId) {
        AccessLog log = new AccessLog();
        log.setAccessTime(Instant.now());
        log.setStatus("SUCCESS");
        repo.save(log);
    }
}
