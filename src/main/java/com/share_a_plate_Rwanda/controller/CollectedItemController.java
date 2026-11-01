package com.share_a_plate_Rwanda.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.share_a_plate_Rwanda.Service.CollectedItemService;
import com.share_a_plate_Rwanda.model.CollectedItem;

@RestController 
@RequestMapping ("/api/collected-items")
public class CollectedItemController {
    @Autowired 
    private CollectedItemService collectedItemService;
    
    //e1
    @PostMapping ("/save")
    public ResponseEntity<String> saveCollectedItem(@RequestParam UUID donationId,@RequestBody CollectedItem collectedItem) {
        String message = collectedItemService.saveCollectedItem(donationId,collectedItem);
        if (message.equals("Collected item saved successfully")) {
            return new ResponseEntity<>(message,HttpStatus.CREATED);

            
        }
        else if(message.equals("Collected items are only for COLLECTED donations")) {
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
    //e2
    @GetMapping ("/recipient/{registrationNumber}")
    public ResponseEntity<List<CollectedItem>> getHistoryOfRecipent(@PathVariable String registrationNumber) {
        return new ResponseEntity<>(collectedItemService.getHistoryOfRecipient(registrationNumber),HttpStatus.OK);

    }
    //e3
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteCollectedItem(@PathVariable UUID id) {
        String message = collectedItemService.deleteCollectedItem(id);
        if(message.equals("Collected item deleted successfully")){
            return new ResponseEntity<>(message,HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }



}
