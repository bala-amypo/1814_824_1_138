package com.example.repository;

import com.example.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    // Example of a custom query method to find logs by username
    List<AccessLog> findByUsername(String username);

    // Example: find logs after a certain timestamp
    List<AccessLog> findByTimestampAfter(java.time.LocalDateTime timestamp);
}
