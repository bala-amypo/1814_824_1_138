package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "key_share_requests")
public class KeyShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant shareStart;
    private Instant shareEnd;

    @ManyToOne
    @JoinColumn(name = "digital_key_id")
    private DigitalKey digitalKey;

    @ManyToOne
    @JoinColumn(name = "shared_by_id")
    private Guest sharedBy;

    @ManyToOne
    @JoinColumn(name = "shared_with_id")
    private Guest sharedWith;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Instant getShareStart() { return shareStart; }
    public void setShareStart(Instant shareStart) { this.shareStart = shareStart; }
    public Instant getShareEnd() { return shareEnd; }
    public void setShareEnd(Instant shareEnd) { this.shareEnd = shareEnd; }
    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) { this.digitalKey = digitalKey; }
    public Guest getSharedBy() { return sharedBy; }
    public void setSharedBy(Guest sharedBy) { this.sharedBy = sharedBy; }
    public Guest getSharedWith() { return sharedWith; }
    public void setSharedWith(Guest sharedWith) { this.sharedWith = sharedWith; }
}
