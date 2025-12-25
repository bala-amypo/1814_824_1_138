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