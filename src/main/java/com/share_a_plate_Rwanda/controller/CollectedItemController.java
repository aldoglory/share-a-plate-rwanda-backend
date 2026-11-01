package com.share_a_plate_Rwanda.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



}
