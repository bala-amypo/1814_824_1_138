package com.example.demo.model;


import jakarta.persistence.*;
import java.util.*;


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
private boolean verified;
private boolean active = true;
private String role = "ROLE_USER";


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
public boolean getVerified() { return verified; }
public void setVerified(boolean verified) { this.verified = verified; }
public boolean getActive() { return active; }
public void setActive(boolean active) { this.active = active; }
public String getRole() { return role; }
public void setRole(String role) { this.role = role; }
}