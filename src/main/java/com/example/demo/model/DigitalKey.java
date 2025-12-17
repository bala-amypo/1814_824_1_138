package com.example.demo.model;   // keep SAME package as RoomBooking

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Entity
public class DigitalKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many keys -> One booking
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private RoomBooking booking;

    @Column(unique = true, nullable = false)
    private String keyValue;

    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    private Boolean active = true;

    // ✅ Empty constructor (required by JPA)
    public DigitalKey() {
    }

    // ✅ Parameterized constructor
    public DigitalKey(RoomBooking booking, String keyValue,
                      LocalDateTime issuedAt, LocalDateTime expiresAt,
                      Boolean active) {
        this.booking = booking;
        this.keyValue = keyValue;
        this.issuedAt = issuedAt;
        this.expiresAt = expiresAt;
        this.active = (active != null) ? active : true;
    }

    // ✅ Rules enforcement
    @PrePersist
    @PreUpdate
    private void validateKey() {

        // auto-generate issuedAt if missing
        if (issuedAt == null) {
            issuedAt = LocalDateTime.now();
        }

        // expiresAt ≥ issuedAt
        if (expiresAt != null && expiresAt.isBefore(issuedAt)) {
            throw new IllegalArgumentException(
                "expiresAt must be greater than or equal to issuedAt"
            );
        }

        // only active keys can be shared
        if (Boolean.FALSE.equals(active)) {
            throw new IllegalStateException(
                "Inactive digital keys cannot be shared"
            );
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public RoomBooking getBooking() {
        return booking;
    }

    public void setBooking(RoomBooking booking) {
        this.booking = booking;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
