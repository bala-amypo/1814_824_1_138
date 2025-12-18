package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.RoomBooking;
import com.example.demo.service.RoomBookingService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/bookings")
@Tag(name = "Room Booking API", description = "Room Booking Management APIs")
public class RoomBookingController {

    @Autowired
    private RoomBookingService bookingService;

    @PostMapping
    public RoomBooking createBooking(@RequestBody RoomBooking booking) {
        return bookingService.createBooking(booking);
    }


    @PutMapping("/{id}")
    public RoomBooking updateBooking(@PathVariable Long id,
                                     @RequestBody RoomBooking booking) {
        return bookingService.updateBooking(id, booking);
    }

    
    @GetMapping("/{id}")
    public RoomBooking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }


    @GetMapping("/guest/{guestId}")
    public List<RoomBooking> getBookingsForGuest(@PathVariable Long guestId) {
        return bookingService.getBookingsForGuest(guestId);
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateBooking(@PathVariable Long id) {
        bookingService.deactivateBooking(id);
        return "Booking deactivated successfully";
    }
}
