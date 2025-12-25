package com.example.demo.repository;

import com.example.demo.model.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {
    List<DigitalKey> findByRoomBookingId(Long bookingId);
}
