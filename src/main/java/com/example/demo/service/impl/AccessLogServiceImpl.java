package com.example.service.impl;

import java.util.List;

import com.example.model.AccessLog;
import com.example.repository.AccessLogRepository;
import com.example.repository.KeyRepository;
import com.example.service.AccessLogService;

public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;
    private final KeyRepository keyRepository;

    public AccessLogServiceImpl(AccessLogRepository accessLogRepository,
                                KeyRepository keyRepository) {
        this.accessLogRepository = accessLogRepository;
        this.keyRepository = keyRepository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {

        boolean isValidKey = keyRepository.existsById(log.getKeyId());

        if (isValidKey) {
            log.setResult("SUCCESS");
        } else {
            log.setResult("DENIED");
        }

        return accessLogRepository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return accessLogRepository.findByKeyId(keyId);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return accessLogRepository.findByGuestId(guestId);
    }
}
