package com.example.service;

import java.util.List;
import com.example.model.AccessLog;

public interface AccessLogService {

    /**
     * Checks key validity and sets result as SUCCESS or DENIED.
     *
     * @param log AccessLog object
     * @return saved AccessLog
     */
    AccessLog createLog(AccessLog log);

    /**
     * Fetch logs by key ID.
     *
     * @param keyId key identifier
     * @return list of AccessLog
     */
    List<AccessLog> getLogsForKey(Long keyId);

    /**
     * Fetch logs by guest ID.
     *
     * @param guestId guest identifier
     * @return list of AccessLog
     */
    List<AccessLog> getLogsForGuest(Long guestId);
}
