package com.example.demo.model;


import jakarta.persistence.*;
import java.time.*;


@Entity
public class AccessLog {
@Id
@GeneratedValue
private Long id;


@ManyToOne private DigitalKey digitalKey;
@ManyToOne private Guest guest;
private Instant accessTime;
private String result;
public DigitalKey getDigitalKey() { return digitalKey; }
public void setDigitalKey(DigitalKey d) { this.digitalKey = d; }
public Guest getGuest() { return guest; }
public void setGuest(Guest g) { this.guest = g; }
public Instant getAccessTime() { return accessTime; }
public void setAccessTime(Instant a) { this.accessTime = a; }
public String getResult() { return result; }
public void setResult(String r) { this.result = r; }
}