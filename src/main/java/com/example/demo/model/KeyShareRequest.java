package com.example.demo.model;


import jakarta.persistence.*;
import java.time.*;


@Entity
public class KeyShareRequest {
@Id
@GeneratedValue
private Long id;


@ManyToOne private DigitalKey digitalKey;
@ManyToOne private Guest sharedBy;
@ManyToOne private Guest sharedWith;


private Instant shareStart;
private Instant shareEnd;


public DigitalKey getDigitalKey() { return digitalKey; }
public void setDigitalKey(DigitalKey d) { this.digitalKey = d; }
public Guest getSharedBy() { return sharedBy; }
public void setSharedBy(Guest g) { this.sharedBy = g; }
public Guest getSharedWith() { return sharedWith; }
public void setSharedWith(Guest g) { this.sharedWith = g; }
public Instant getShareStart() { return shareStart; }
public void setShareStart(Instant s) { this.shareStart = s; }
public Instant getShareEnd() { return shareEnd; }
public void setShareEnd(Instant e) { this.shareEnd = e; }
}