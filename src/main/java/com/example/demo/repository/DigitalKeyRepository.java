package com.example.demo.repository;

import com.example.demo.model.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    Optional<DigitalKey> findByKeyValue(String keyValue);
}
