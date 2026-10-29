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

import com.share_a_plate_Rwanda.Service.DonorService;
import com.share_a_plate_Rwanda.model.Donor;

@RestController 
@RequestMapping ("/api/donors")
public class DonorController {
    @Autowired 
    private DonorService donorService;

    @PostMapping("/save")
    public ResponseEntity<String> saveDonor(@RequestBody Donor donor) {
        String message = donorService.saveDonor(donor);
        if (message.equals("Donor saved successfully")) {
            return new ResponseEntity<>(message,HttpStatus.CREATED);

            
        }
        else
        {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<Donor>> getAllDonors() {
        return new ResponseEntity<>(donorService.getAllDonors(),HttpStatus.OK);

    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getDonorById(@PathVariable UUID id) {
        Optional<Donor> donor = donorService.getDonorById(id);
        if (donor.isPresent()) {
            return new ResponseEntity<>(donor.get(),HttpStatus.OK);

            
        }
        else{
            return new ResponseEntity<>("The donor with that id does not exist", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateDonor(@PathVariable UUID id, @RequestBody Donor donor) {
        String message = donorService.updateDonor(id, donor);
        if (message.equals("Donor updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);

            
        }
        else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable UUID id) {
        String message = donorService.deleteDonor(id);
        if (message.equals("Donor deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);

            
        }
        else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

}

