package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;

import java.time.LocalDate;
import java.util.List;

public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository repository;

    public RoomBookingServiceImpl(RoomBookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {
        if (booking.getCheckOutDate().isBefore(booking.getCheckInDate())) {
            throw new IllegalArgumentException("Check-in date must be before check-out");
        }
        return repository.save(booking);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking update) {
        RoomBooking b = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + id));

        b.setCheckInDate(update.getCheckInDate());
        b.setCheckOutDate(update.getCheckOutDate());
        return repository.save(b);
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return repository.findByGuestId(guestId);
    }
}
