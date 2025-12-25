package com.example.demo.service;

import com.example.demo.model.AccessLog;
import java.util.List;

public interface AccessLogService {
    AccessLog createLog(AccessLog log);
    AccessLog getLogById(Long id);
    List<AccessLog> getAllLogs();
    void deleteLog(Long id);
}
