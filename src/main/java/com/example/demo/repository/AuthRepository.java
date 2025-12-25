package com.example.demo.repository;

import com.example.demo.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Guest, Long> {

    // Find user by username for login
    Optional<Guest> findByUsername(String username);

    // Check if username already exists (for registration)
    Boolean existsByUsername(String username);

    // Check if email already exists (for registration)
    Boolean existsByEmail(String email);
}
