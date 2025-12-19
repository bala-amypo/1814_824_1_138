package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByUsername(String username);
}
