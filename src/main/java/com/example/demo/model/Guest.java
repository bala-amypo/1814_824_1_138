package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Guest {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String fullName;
    private String phoneNumber;
    private String role = "ROLE_USER";
    private Boolean verified = false;
    private Boolean active = true;

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest g = (Guest) o;
        return Objects.equals(id, g.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
