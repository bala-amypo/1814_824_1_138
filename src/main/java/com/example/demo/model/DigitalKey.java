package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class DigitalKey {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String keyValue;
    private Instant issuedAt;
    private Instant expiresAt;
    private boolean active;

    @ManyToOne
    private RoomBooking booking;

    @ManyToOne
    private Guest guest;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }
    public Instant getIssuedAt() { return issuedAt; }
    public void setIssuedAt(Instant issuedAt) { this.issuedAt = issuedAt; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public RoomBooking getBooking() { return booking; }
    public void setBooking(RoomBooking booking) { this.booking = booking; }
    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }
}
