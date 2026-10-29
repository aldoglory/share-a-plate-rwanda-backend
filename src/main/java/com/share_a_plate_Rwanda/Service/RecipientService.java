package com.share_a_plate_Rwanda.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.print.DocFlavor.READER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share_a_plate_Rwanda.model.Recipient;
import com.share_a_plate_Rwanda.repository.RecipientRepository;

@Service 
public class RecipientService {
    @Autowired 
    private RecipientRepository recipientRepository;
    public String saveRecipient(Recipient recipient) {
        if (recipientRepository.existsByRegistrationNumber(recipient.getRegistrationNumber())) {
            return "A recipient with that reg number already exist";

        }
        recipientRepository.save(recipient);
        return "Recipient saved successfully";
    }
    public List<Recipient> getAllRecipients() {
        return recipientRepository.findAll();
    }
    public Optional<Recipient> getRecipientById(UUID id) {
        return recipientRepository.findById(id);

    }
    public String updateRecipient(UUID id , Recipient recipient) {
        Optional<Recipient> existing = recipientRepository.findById(id);
        if(existing.isEmpty()) {
            return  "The recipient with that id does not exist";

        }
        Recipient toUpdate = existing.get();
        toUpdate.setOrganisationName(recipient.getOrganasationName());
        toUpdate.setPhone(recipient.getPhone());
        toUpdate.setDistrict(recipient.getDistrict());
        recipientRepository.save(toUpdate);
        return "Recipient updated successfully";
        
    }
    public String deleteRecipient(UUID id ){
        if (!recipientRepository.existsById(id)) {
            return "The recipient with that id does not exist";
        }
        recipientRepository.deleteById(id);
        return "Recipient deleted successfully";
    }
    //B1
    public List<Recipient> getRecipientsByOrganisationName(String organisationName) {
        return recipientRepository.findByOrganisationNameIgnoreCaseOrderByContactNameAsc(organisationName);
    }
    //c4
    public List<Recipient> getRecipientsOfDonor(UUID donorId){
        return recipientRepository.findRecipientsOfDonor(donorId);
    }
}
