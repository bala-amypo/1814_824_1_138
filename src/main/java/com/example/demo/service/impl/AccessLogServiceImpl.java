package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.service.AccessLogService;
import org.springframework.stereotype.Service;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository repository;

    public AccessLogServiceImpl(AccessLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccessLog logAccess(AccessLog accessLog) {
        return repository.save(accessLog);
    }
}
