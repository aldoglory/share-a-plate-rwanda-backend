package com.share_a_plate_Rwanda.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.share_a_plate_Rwanda.Service.DonorService;
import com.share_a_plate_Rwanda.Service.RecipientService;
import com.share_a_plate_Rwanda.model.Donor;
import com.share_a_plate_Rwanda.model.Recipient;

@RestController 
@RequestMapping ("/api/recipients")
public class RecipientController {
    @Autowired 
    private RecipientService recipientService;
    //c4
    @Autowired 
    private DonorService donorService;
    @PostMapping ("/save")
    public ResponseEntity<String> saveRecipieEntity(@RequestBody Recipient recipient)  {
        String message = recipientService.saveRecipient(recipient);
        if (message.equals("Recipient saved successfully")) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);

            
        }
        else if (message.equals("A recipient with that registration number already exists")){
            return new ResponseEntity<>(message,HttpStatus.CONFLICT);
        }
        else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }  
    @GetMapping ("/all")
    public ResponseEntity<List<Recipient>> getAllRecipients() {
        return new ResponseEntity<>(recipientService.getAllRecipients(), HttpStatus.OK);

        
    }
    //b1
    @GetMapping ("/by-organisation-name")
    public ResponseEntity<List<Recipient>> getRecipientByOrganisationName(@RequestParam String organisationName) {
        return new ResponseEntity<>(recipientService.getRecipientsByOrganisationName(organisationName), HttpStatus.OK);

    }
    @GetMapping("/of-donor/{donorId}")
    public ResponseEntity<?>getRecipientsOfDonor(@PathVariable  UUID donorId) {
        Optional<Donor> donor = donorService.getDonorById(donorId);
        if(donor.isEmpty()) {
            return new ResponseEntity<>("The donor with that id does not exist",HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(recipientService.getRecipientsOfDonor(donorId),HttpStatus.OK);
        }
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getRecipientById(@PathVariable UUID id) {
        Optional<Recipient> recipient = recipientService.getRecipientById(id);
     if(recipient.isPresent()) {
        return new ResponseEntity<>(recipient.get(),HttpStatus.OK);


     }
     else {
        return new ResponseEntity<>("The recipient with that id does not exist",HttpStatus.NOT_FOUND);
     }
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateRecipient(@PathVariable UUID id, @RequestBody Recipient recipient){
        String message = recipientService.updateRecipient(id,recipient);
        if (message.equals("Recipient updated successfully")){
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteRecipient(@PathVariable UUID id ){
        String message = recipientService.deleteRecipient(id);
        if (message.equals("Recipient deleted successfully")) {
            return new ResponseEntity<>(message,HttpStatus.OK);
            
        }
        else{
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
}

