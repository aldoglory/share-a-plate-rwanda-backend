package com.share_a_plate_Rwanda.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share_a_plate_Rwanda.model.Donation;
import com.share_a_plate_Rwanda.model.DonationStatus;
import com.share_a_plate_Rwanda.model.Donor;
import com.share_a_plate_Rwanda.model.Recipient;
import com.share_a_plate_Rwanda.repository.DonationRepository;
import com.share_a_plate_Rwanda.repository.DonorRepository;
import com.share_a_plate_Rwanda.repository.RecipientRepository;

@Service 
public class DonationService {
@Autowired 
private DonationRepository donationRepository;
@Autowired 
private RecipientRepository recipientRepository;
@Autowired
private DonorRepository donorRepository;

public String saveDonation(Donation donation){
    String check = attachRelations(donation,donation);
    if(check !=null) {
        return check;
    }
    donationRepository.save(donation);
    return "Donation saved successfully";
}

public List<Donation> getAllDonations() {
    return donationRepository.findAll();
}

public Optional<Donation> getDonationById(UUID id) {
    return donationRepository.findById(id);
}
public String updateDonation(UUID id, Donation donation) {
    Optional<Donation> existing = donationRepository.findById(id);
    if (existing.isEmpty()) {
        return "The donation with that id does not exist";

        
    }
    Donation toUpdate = existing.get();
    String check = attachRelations(donation,toUpdate);
    if (check !=null) {
        return check;
    }
    toUpdate.setDonationDate(donation.getDonationDate());
    toUpdate.setStatus(donation.geStatus());
    toUpdate.setFoodDescription(donation.getFoodDescription());
    toUpdate.setEstimatedPortions(donation.getEstimatedPortions());
    donationRepository.save(toUpdate);
    return "Donation updated successfully";
}

public String deleteDonation(UUID id ) {
    if (!donationRepository.existsById(id)) {
        return "The donation with that id does not exist";
        
    }
    donationRepository.deleteById(id);
    return "Donation deleted successfully";
}
//B2
public List<Donation> getDonationsByStatus(DonationStatus status) {
    return donationRepository.findByStatusOrderByDonationDateAsc(status);
}
//b3
public List<Donation>getDonationsBetween(LocalDate start,LocalDate end) {
    return donationRepository.findByDonationDateBetweenOrderByDonationDateAsc(start,end);
}
// getting recipient and donor from id's
private String attachRelations(Donation source, Donation target) {
    if(source.gRecipient() !=null && source.gRecipient().getId() !=null){
        Optional<Recipient> recipient = recipientRepository.findById(source.gRecipient().getId());
        if (recipient.isEmpty()) {
            return "The recipient with that id does not exist";

            
        }
        target.setRecipient(recipient.get());

    }
    else{
        target.setRecipient(null);

    }
    if (source.getDonor() !=null && source.getDonor().getId() !=null) {
        Optional<Donor> donor = donorRepository.findById(source.getDonor().getId());
        if (donor.isEmpty()){
            return "The donor with that id does not exist";
        }
        target.setDonor(donor.get());

    } 
    else {
        target.setDonor(null);
    }
    return null;
}
}
