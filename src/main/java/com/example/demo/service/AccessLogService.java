package com.example.demo.service;

import com.example.demo.model.AccessLog;

import java.util.List;

public interface AccessLogService {

    AccessLog logAccess(Long guestId);   // 🔥 ADD THIS

    List<AccessLog> getAllLogs();
}
