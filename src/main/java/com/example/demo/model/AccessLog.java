package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant accessTime;
    private String status;

    @ManyToOne
    private Guest guest;

    public Long getId() { return id; }

    public Instant getAccessTime() { return accessTime; }
    public void setAccessTime(Instant accessTime) { this.accessTime = accessTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }
}
