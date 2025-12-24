package com.example.demo.service;

import com.example.demo.model.Guest;

public interface GuestService {
    Guest registerGuest(Guest guest);
    Guest getGuestById(Long id);
}
