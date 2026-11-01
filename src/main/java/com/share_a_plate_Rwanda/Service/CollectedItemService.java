package com.share_a_plate_Rwanda.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share_a_plate_Rwanda.model.CollectedItem;
import com.share_a_plate_Rwanda.model.Donation;
import com.share_a_plate_Rwanda.model.DonationStatus;
import com.share_a_plate_Rwanda.repository.CollectedItemRepository;
import com.share_a_plate_Rwanda.repository.DonationRepository;

@Service 
public class CollectedItemService {
    @Autowired 
    private CollectedItemRepository collectedItemRepository;

    @Autowired 
    private DonationRepository donationRepository;

    //e1
    public String saveCollectedItem(UUID donationId,CollectedItem collectedItem){
    Optional<Donation> donationOpt = donationRepository.findById(donationId);
    if(donationOpt.isEmpty()){
        return "The donation with that id does not exist";
    }

    Donation donation = donationOpt.get();
    if (donation.getStatus() != DonationStatus.COLLECTED) {
        return "Collected items are only for COLLECTED donations";

        
    }
    collectedItem.setCollectedDate(LocalDate.now());
    collectedItem.setDonation(donation);
    collectedItemRepository.save(collectedItem);
    return "Collected item saved successfully";
}}
