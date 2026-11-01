package com.share_a_plate_Rwanda.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.share_a_plate_Rwanda.model.Donation;
import com.share_a_plate_Rwanda.model.DonationStatus;

public interface DonationRepository extends JpaRepository<Donation,UUID> {
    //b2
    List<Donation> findByStatusOrderByDonationDateAsc(DonationStatus status);
    //b3
    List<Donation> findByDonationDateBetweenOrderByDonationDateAsc(LocalDate start,LocalDate end);
    //d1
    boolean existsByDonorIdAndDonationDateAndStatusNot(UUID donorId,LocalDate donationDate, DonationStatus status);
    //F1
    @Query ("SELECT d.status, COUNT(d) FROM Donation d GROUP BY d.status")
    List<Object[]>countDonationsByStatus();
    //F2
    @Modifying 
    @Query ("UPDATE Donation d SET d.status = :expired WHERE d.donor.id = :donorId AND d.donationDate = :date AND d.status <> :collected")
    int cancelDonorDay(@Param ("donorId") UUID donorId,@Param("date") LocalDate date, @Param ("expired") DonationStatus expired, @Param("collected") DonationStatus collected);
    
    
}
