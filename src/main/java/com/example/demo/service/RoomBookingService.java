package com.example.demo.service;

import com.example.demo.model.RoomBooking;
import java.util.List;

public interface RoomBookingService {
    RoomBooking createBooking(RoomBooking booking);
    RoomBooking getBookingById(Long id);
    List<RoomBooking> getAllBookings();
    RoomBooking updateBooking(Long id, RoomBooking booking);
    void deleteBooking(Long id);
}
