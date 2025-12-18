package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/guests")
@Tag(name = "Guest API")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @PostMapping
    public Guest createGuest(@RequestBody Guest guest) {
        return guestService.createGuest(guest);
    }

    @PutMapping("/{id}")
    public Guest updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        return guestService.updateGuest(id, guest);
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable Long id) {
        return guestService.getGuestById(id);
    }

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }

    @PutMapping("/{id}/deactivate")
    public String deactivateGuest(@PathVariable Long id) {
        guestService.deactivateGuest(id);
        return "Guest deactivated successfully";
    }
}
