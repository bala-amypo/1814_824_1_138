package com.example.demo.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "guests", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean active = true;
    private boolean verified = false;
    private String role = "ROLE_USER";

    @ManyToMany(mappedBy = "roommates")
    private Set<RoomBooking> bookings = new HashSet<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Set<RoomBooking> getBookings() { return bookings; }
    public void setBookings(Set<RoomBooking> bookings) { this.bookings = bookings; }
}
