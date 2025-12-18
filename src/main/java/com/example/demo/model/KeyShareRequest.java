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

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // ----- Business Rules Validation -----
    @PrePersist
    public void onCreate() {

        if (shareEnd.isBefore(shareStart) || shareEnd.isEqual(shareStart)) {
            throw new IllegalArgumentException("shareEnd must be after shareStart");
        }

        if (sharedBy.equals(sharedWith)) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be the same guest");
        }

        this.createdAt = LocalDateTime.now();
    }

    // ----- Getters & Setters -----

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
