package com.example.demo.controller;

import com.example.demo.model.RoomBooking;
import com.example.demo.service.RoomBookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class RoomBookingController {

    private final RoomBookingService roomBookingService;

    public RoomBookingController(RoomBookingService roomBookingService) {
        this.roomBookingService = roomBookingService;
    }

    @PostMapping
    public RoomBooking createBooking(@RequestBody RoomBooking booking) {
        return roomBookingService.createBooking(booking);
    }

    @PutMapping("/{id}")
    public RoomBooking updateBooking(@PathVariable Long id, @RequestBody RoomBooking booking) {
        return roomBookingService.updateBooking(id, booking);
    }

    @GetMapping("/guest/{guestId}")
    public List<RoomBooking> getBookingsForGuest(@PathVariable Long guestId) {
        return roomBookingService.getBookingsForGuest(guestId);
    }
}
