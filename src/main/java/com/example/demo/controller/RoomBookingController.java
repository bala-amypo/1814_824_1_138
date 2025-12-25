package com.example.demo.controller;

import com.example.demo.model.RoomBooking;
import com.example.demo.service.RoomBookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class RoomBookingController {

    private final RoomBookingService bookingService;

    public RoomBookingController(RoomBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public RoomBooking create(@RequestBody RoomBooking booking) {
        return bookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public RoomBooking update(@PathVariable Long id,
                              @RequestBody RoomBooking booking) {
        return bookingService.updateBooking(id, booking);
    }

    @GetMapping("/guest/{guestId}")
    public List<RoomBooking> byGuest(@PathVariable Long guestId) {
        return bookingService.getBookingsForGuest(guestId);
    }
}
