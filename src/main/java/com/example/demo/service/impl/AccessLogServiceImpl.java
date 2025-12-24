package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.model.Guest;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.AccessLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;   // ✅ REQUIRED

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;
    private final GuestRepository guestRepository;

    public AccessLogServiceImpl(
            AccessLogRepository accessLogRepository,
            GuestRepository guestRepository) {
        this.accessLogRepository = accessLogRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public AccessLog logAccess(Long guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found"));

        AccessLog log = new AccessLog();
        log.setGuest(guest);
        log.setAccessTime(LocalDateTime.now());

        return accessLogRepository.save(log);
    }

    @Override
    public List<AccessLog> getAllLogs() {
        return accessLogRepository.findAll();
    }
}
