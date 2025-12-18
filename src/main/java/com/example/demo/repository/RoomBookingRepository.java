package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RoomBooking;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
    
}
