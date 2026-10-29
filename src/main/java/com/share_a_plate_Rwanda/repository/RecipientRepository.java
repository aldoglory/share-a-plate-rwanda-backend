package com.share_a_plate_Rwanda.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.share_a_plate_Rwanda.model.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, UUID> {
    boolean existsByRegistrationNumber(String registrationNumber);
    //B1
    List<Recipient> findByOrganisationNameIgnoreCaseOrderByContactNameAsc(String organisationName);
    //c4
    @Query("SELECT DISTINCT dn.recipient FROM Donation dn WHERE dn.donor.id= :donorId")
    List<Recipient>findRecipientsOfDonor(@Param("donorId") UUID donorId);
    
}
