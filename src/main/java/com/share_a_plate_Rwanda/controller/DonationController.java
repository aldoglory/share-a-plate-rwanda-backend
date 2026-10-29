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
import org.springframework.web.bind.annotation.RestController;

import com.share_a_plate_Rwanda.Service.DonationService;
import com.share_a_plate_Rwanda.model.Donation;

@RestController 
@RequestMapping ("/api/donations")
public class DonationController {
    @Autowired 
    private DonationService donationService;
    @PostMapping ("/save")
    public ResponseEntity<String> saveDonation(@RequestBody Donation donation ){
      String message = donationService.saveDonation(donation);
      if (message.equals("Donation saved successfully")) {
        return new ResponseEntity<>(message,HttpStatus.CREATED);

        
      }
      else{
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

      }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Donation>> getAllDonations(){
        return new ResponseEntity<>(donationService.getAllDonations(),HttpStatus.OK);
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getDonationById(@PathVariable UUID id ){
        Optional<Donation> donation = donationService.getDonationById(id);
        if (donation.isPresent()) {
            return new ResponseEntity<>(donation.get(),HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>("The donation with that id does not exist",HttpStatus.NOT_FOUND);

        }
       
    }
     @PutMapping ("/update/{id}")
        public ResponseEntity<String> updateDonation(@PathVariable UUID id, @RequestBody Donation donation) {
            String message = donationService.updateDonation(id, donation);
            if (message.equals("Donation updated successfully")) {
                return new ResponseEntity<>(message,HttpStatus.OK);
                
            }
            else {
                return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
            }
            
        }
        @DeleteMapping ("/delete/{id}")
        public ResponseEntity<String> deleteDonation(@PathVariable UUID id) {
            String message = donationService.deleteDonation(id);
            if (message.equals("Donation deleted successfully")) {
                return new ResponseEntity<>(message,HttpStatus.OK);
                
            }
            else{
                return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
            }
        }
    
}
