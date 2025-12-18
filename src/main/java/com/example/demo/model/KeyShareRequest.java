package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "key_share_requests")
public class KeyShareRequest {

    public enum KeyShareStatus {
        PENDING,
        APPROVED,
        REJECTED
    }

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

    @PrePersist
    protected void onCreate() {

        if (shareEnd == null || shareStart == null || !shareEnd.isAfter(shareStart)) {
            throw new IllegalArgumentException("shareEnd must be after shareStart");
        }

        if (sharedBy != null && sharedBy.equals(sharedWith)) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }

        this.createdAt = LocalDateTime.now();
        this.status = KeyShareStatus.PENDING;
    }

    // getters & setters omitted for brevity
}
