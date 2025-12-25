package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.AccessLogService;

import java.time.Instant;
import java.util.List;

public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository repo;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;
    private final KeyShareRequestRepository shareRepo;

    public AccessLogServiceImpl(AccessLogRepository r,
                                DigitalKeyRepository k,
                                GuestRepository g,
                                KeyShareRequestRepository s) {
        this.repo = r;
        this.keyRepo = k;
        this.guestRepo = g;
        this.shareRepo = s;
    }

    @Override
    public AccessLog createLog(AccessLog log) {

        if (log.getAccessTime().isAfter(Instant.now())) {
            throw new IllegalArgumentException("Access time in future");
        }

        DigitalKey key = keyRepo.findById(log.getDigitalKey().getId()).orElseThrow();
        Guest guest = guestRepo.findById(log.getGuest().getId()).orElseThrow();

        boolean allowed = key.getActive()
                && Instant.now().isAfter(key.getIssuedAt())
                && Instant.now().isBefore(key.getExpiresAt());

        log.setResult(allowed ? "SUCCESS" : "DENIED");
        return repo.save(log);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return repo.findByGuestId(guestId);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return repo.findByDigitalKeyId(keyId);
    }
}
