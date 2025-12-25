package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.service.AccessLogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository logRepository;

    public AccessLogServiceImpl(AccessLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {
        return logRepository.save(log);
    }

    @Override
    public AccessLog getLogById(Long id) {
        return logRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AccessLog not found with id " + id));
    }

    @Override
    public List<AccessLog> getAllLogs() {
        return logRepository.findAll();
    }

    @Override
    public void deleteLog(Long id) {
        logRepository.deleteById(id);
    }
}
