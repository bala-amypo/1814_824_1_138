package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DigitalKey;

public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    List<DigitalKey> findByBookingId(Long bookingId);

    boolean existsByKeyValue(String keyValue);
}
