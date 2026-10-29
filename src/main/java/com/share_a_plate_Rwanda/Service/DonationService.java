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
// d1 modifying A2
public String saveDonation(Donation donation){
    //rule 1 date cannot be in the past
    if (donation.getDonationDate().isBefore(LocalDate.now())) {
        return "Donation date cannot be in the past";
        
    }
    String check = attachRelations(donation,donation);
    if(check !=null) {
        return check;
    }
    //rule 2 donor cannot have another active donation on that date
    if (donation.getDonor() !=null && donationRepository.existsByDonorIdAndDonationDateAndStatusNot(donation.getDonor().getId(),donation.getDonationDate(),DonationStatus.EXPIRED)) {
        return "Donor already has an active donation on that date";
        
    }
    //rule 3 a new donation always starts as Posted
    donation.setStatus(DonationStatus.POSTED);

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
//d2
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
//d2
public String updateDonationStatus(UUID id, DonationStatus newStatus) {
    Optional<Donation> existing = donationRepository.findById(id);
    if (existing.isEmpty()) {
        return "The donation with that id does not exist";
        
    } 
    Donation donation = existing.get();
    DonationStatus current = donation.geStatus();
    if (!isAllowedMove(current,newStatus)) {
        return "Can not change status from " + current + "to" + newStatus;
        
    }
    donation.setStatus(newStatus);
    donationRepository.save(donation);
    return "Donation status updated successfully";
}

//d2
private boolean isAllowedMove(DonationStatus current, DonationStatus newStatus) {
    if (current == DonationStatus.POSTED) {
        return newStatus == DonationStatus.MATCHED || newStatus == DonationStatus.EXPIRED;

    }
    else if(current == DonationStatus.MATCHED){
        return newStatus == DonationStatus.COLLECTED || newStatus == DonationStatus.EXPIRED;

    }
    else{
        //collected and expired are final
        return false;
    }
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
