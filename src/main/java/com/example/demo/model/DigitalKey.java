package com.example.demo.model;


import jakarta.persistence.*;
import java.time.*;


@Entity
public class DigitalKey {
@Id
@GeneratedValue
private Long id;
private String keyValue;
private Instant issuedAt;
private Instant expiresAt;
private boolean active = true;


@ManyToOne
private RoomBooking booking;
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getKeyValue() { return keyValue; }
public void setKeyValue(String k) { this.keyValue = k; }
public Instant getIssuedAt() { return issuedAt; }
public void setIssuedAt(Instant i) { this.issuedAt = i; }
public Instant getExpiresAt() { return expiresAt; }
public void setExpiresAt(Instant e) { this.expiresAt = e; }
public boolean getActive() { return active; }
public void setActive(boolean a) { this.active = a; }
public RoomBooking getBooking() { return booking; }
public void setBooking(RoomBooking b) { this.booking = b; }
}