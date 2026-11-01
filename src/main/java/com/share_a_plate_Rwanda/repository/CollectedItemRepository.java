package com.share_a_plate_Rwanda.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.share_a_plate_Rwanda.model.CollectedItem;
import com.share_a_plate_Rwanda.model.Donation;

@Repository 
public interface CollectedItemRepository extends JpaRepository<CollectedItem,UUID> {
    //e2
    @Query ("SELECT ci FROM CollectedItem ci WHERE ci.donation.recipient.registrationNumber = :registrationNumber ORDER BY ci.collectedDate DESC")
    List<CollectedItem> findByRecipientRegistrationNumber(@Param ("registrationNumber") String registrationNumber);
    //e3
    boolean existsByDonation(Donation donation);
    
}
