package com.share_a_plate_Rwanda.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.share_a_plate_Rwanda.model.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    boolean existsByRegistrationNumber(String registrationNumber);
    //B1
    List<Recipient> findByOrganisationNameIgnoreCaseOrderByContactNameAsc(String organisationName);
}
