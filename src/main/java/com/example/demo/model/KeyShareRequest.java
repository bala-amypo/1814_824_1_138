package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "key_share_requests")
public class KeyShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "digital_key_id", nullable = false)
    private DigitalKey digitalKey;

    @ManyToOne
    @JoinColumn(name = "shared_by_guest_id", nullable = false)
    private Guest sharedBy;

    @ManyToOne
    @JoinColumn(name = "shared_with_guest_id", nullable = false)
    private Guest sharedWith;

    @Column(nullable = false)
    private LocalDateTime shareStart;

    @Column(nullable = false)
    private LocalDateTime shareEnd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KeyShareStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ---------- Lifecycle callback ----------
    @PrePersist
    protected void onCreate() {

        // Rule 1: shareEnd > shareStart
        if (shareEnd == null || shareStart == null || !shareEnd.isAfter(shareStart)) {
            throw new IllegalArgumentException("shareEnd must be after shareStart");
        }

        // Rule 2: sharedBy ≠ sharedWith
        if (sharedBy != null && sharedBy.equals(sharedWith)) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be the same guest");
        }

        this.createdAt = LocalDateTime.now();
        this.status = KeyShareStatus.PENDING;
    }

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public DigitalKey getDigitalKey() {
        return digitalKey;
    }

    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }

    public Guest getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(Guest sharedBy) {
        this.sharedBy = sharedBy;
    }

    public Guest getSharedWith() {
        return sharedWith;
    }

    public void setSharedWith(Guest sharedWith) {
        this.sharedWith = sharedWith;
    }

    public LocalDateTime getShareStart() {
        return shareStart;
    }

    public void setShareStart(LocalDateTime shareStart) {
        this.shareStart = shareStart;
    }

    public LocalDateTime getShareEnd() {
        return shareEnd;
    }

    public void setShareEnd(LocalDateTime shareEnd) {
        this.shareEnd = shareEnd;
    }

    public KeyShareStatus getStatus() {
        return status;
    }

    public void setStatus(KeyShareStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
