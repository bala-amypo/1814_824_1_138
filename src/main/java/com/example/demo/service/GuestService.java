package com.example.service;

import java.util.List;
import com.example.model.Guest;

public interface GuestService {

    Guest createGuest(Guest guest);

    Guest updateGuest(Long id, Guest guest);

    Guest getGuestById(Long id);

    List<Guest> getAllGuests();

    void deactivateGuest(Long id);
}
