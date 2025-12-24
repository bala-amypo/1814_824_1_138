package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.AccessLogService;

import java.time.Instant;
import java.util.List;

public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository logRepo;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;
    private final KeyShareRequestRepository shareRepo;

    public AccessLogServiceImpl(
            AccessLogRepository logRepo,
            DigitalKeyRepository keyRepo,
            GuestRepository guestRepo,
            KeyShareRequestRepository shareRepo) {
        this.logRepo = logRepo;
        this.keyRepo = keyRepo;
        this.guestRepo = guestRepo;
        this.shareRepo = shareRepo;
    }

    @Override
    public AccessLog createLog(AccessLog log) {
        if (log.getAccessTime().isAfter(Instant.now())) {
            throw new IllegalArgumentException("Access time cannot be in future");
        }

        DigitalKey key = keyRepo.findById(log.getDigitalKey().getId()).orElse(null);
        Guest guest = guestRepo.findById(log.getGuest().getId()).orElse(null);

        if (key != null && key.getActive()) {
            log.setResult("SUCCESS");
        } else {
            log.setResult("DENIED");
        }
        return logRepo.save(log);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return logRepo.findByGuestId(guestId);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return logRepo.findByDigitalKeyId(keyId);
    }
}
