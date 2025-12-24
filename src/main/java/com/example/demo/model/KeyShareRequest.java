package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class KeyShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Guest fromGuest;

    @ManyToOne
    private Guest toGuest;

    public Long getId() { return id; }

    public Guest getFromGuest() { return fromGuest; }
    public void setFromGuest(Guest fromGuest) { this.fromGuest = fromGuest; }

    public Guest getToGuest() { return toGuest; }
    public void setToGuest(Guest toGuest) { this.toGuest = toGuest; }
}
