package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    @Autowired
    private RoomBookingRepository bookingRepository;

    @Override
    public RoomBooking createBooking(RoomBooking booking) {

        if (booking.getCheckInDate().isAfter(booking.getCheckOutDate())) {
            throw new RuntimeException("Check-in date must be before check-out date");
        }

        booking.setActive(true);
        return bookingRepository.save(booking);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking booking) {

        RoomBooking existing = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if (booking.getCheckInDate().isAfter(booking.getCheckOutDate())) {
            throw new RuntimeException("Invalid booking dates");
        }

        existing.setRoomNumber(booking.getRoomNumber());
        existing.setCheckInDate(booking.getCheckInDate());
        existing.setCheckOutDate(booking.getCheckOutDate());

        return bookingRepository.save(existing);
    }


    @Override
    public RoomBooking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }


    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return bookingRepository.findByGuestId(guestId);
    }

    @Override
    public void deactivateBooking(Long id) {
        RoomBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setActive(false);
        bookingRepository.save(booking);
    }
}
