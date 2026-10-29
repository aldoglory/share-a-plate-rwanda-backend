package com.share_a_plate_Rwanda.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.share_a_plate_Rwanda.model.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    boolean existsByRegistrationNumber(String registrationNumber);
    
}
