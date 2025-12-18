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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KeyShareStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.status = KeyShareStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    // ---------- GETTERS ----------

    public Long getId() {
        return id;
    }

    public KeyShareStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // ---------- ❗ REQUIRED SETTER (THIS FIXES THE ERROR) ----------

    public void setStatus(KeyShareStatus status) {
        this.status = status;
    }
}
