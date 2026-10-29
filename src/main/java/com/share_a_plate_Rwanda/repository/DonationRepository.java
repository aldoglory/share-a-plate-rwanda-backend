package com.share_a_plate_Rwanda.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.share_a_plate_Rwanda.model.Donation;

public interface DonationRepository extends JpaRepository<Donation,UUID> {
    
}
