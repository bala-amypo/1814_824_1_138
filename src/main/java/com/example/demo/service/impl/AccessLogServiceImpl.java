package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.KeyRepository;
import com.example.demo.service.AccessLogService;

@Service
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

        if (keyRepository.existsById(log.getKeyId())) {
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
