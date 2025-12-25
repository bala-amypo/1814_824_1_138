package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.model.DigitalKey;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.service.AccessLogService;

import java.time.LocalDateTime;
import java.util.List;

public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository logRepository;
    private final DigitalKeyRepository keyRepository;

    public AccessLogServiceImpl(AccessLogRepository logRepository,
                                DigitalKeyRepository keyRepository) {
        this.logRepository = logRepository;
        this.keyRepository = keyRepository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {

        if (log.getAccessTime().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("future access not allowed");
        }

        DigitalKey key = keyRepository.findById(log.getDigitalKey().getId())
                .orElse(null);

        if (key == null || !key.isActive()
                || key.getExpiresAt().isBefore(LocalDateTime.now())) {
            log.setStatus("DENIED");
        } else {
            log.setStatus("SUCCESS");
        }

        return logRepository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return logRepository.findByGuestId(guestId);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return logRepository.findByDigitalKeyId(keyId);
    }
}
