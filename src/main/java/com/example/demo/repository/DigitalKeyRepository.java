package com.example.demo.repository;

import com.example.demo.model.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {
    Optional<DigitalKey> findByBookingIdAndActiveTrue(Long bookingId);
    List<DigitalKey> findByBookingGuestId(Long guestId);
}
