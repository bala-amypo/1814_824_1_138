package com.example.demo.model;


import jakarta.persistence.*;
import java.time.*;
import java.util.*;


@Entity
public class RoomBooking {
@Id
@GeneratedValue
private Long id;
private String roomNumber;
private LocalDate checkInDate;
private LocalDate checkOutDate;
private boolean active = true;


@ManyToOne
private Guest guest;
@ManyToMany
private Set<Guest> roommates = new HashSet<>();


public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getRoomNumber() { return roomNumber; }
public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
public LocalDate getCheckInDate() { return checkInDate; }
public void setCheckInDate(LocalDate d) { this.checkInDate = d; }
public LocalDate getCheckOutDate() { return checkOutDate; }
public void setCheckOutDate(LocalDate d) { this.checkOutDate = d; }
public boolean getActive() { return active; }
public void setActive(boolean a) { this.active = a; }
public Guest getGuest() { return guest; }
public void setGuest(Guest g) { this.guest = g; }
public Set<Guest> getRoommates() { return roommates; }
}