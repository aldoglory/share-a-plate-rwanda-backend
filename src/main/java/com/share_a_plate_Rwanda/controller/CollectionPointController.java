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

import com.share_a_plate_Rwanda.Service.CollectionPointService;
import com.share_a_plate_Rwanda.model.CollectionPoint;

@RestController 
@RequestMapping ("/api/collection-points")
public class CollectionPointController {
    @Autowired 
    private CollectionPointService collectionPointService;
    
    @PostMapping ("/save")
    public ResponseEntity<String> saveCollectionPoint(@RequestBody CollectionPoint collectionPoint) {
        String message = collectionPointService.saveCollectionPoint(collectionPoint);
        if (message.equals("Collection point saved successfully")) {
            return new ResponseEntity<>(message,HttpStatus.CREATED);

            
        }
        else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

        }
    }
    @GetMapping ("/all")
    public ResponseEntity<List<CollectionPoint>> getAllCollectionPoints() {
        return new ResponseEntity<>(collectionPointService.getAllCollectionPoints(),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCollectionPointById(@PathVariable UUID id) {
        Optional<CollectionPoint> collectionPoint = collectionPointService.getCollectionPointById(id);
        if(collectionPoint.isPresent())
        {
            return new ResponseEntity<>(collectionPoint.get(),HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>("The collection point withthat id does not exist", HttpStatus.NOT_FOUND);

        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCollectionPoint(@PathVariable UUID id,@RequestBody CollectionPoint collectionPoint){
        String message = collectionPointService.updateCollectionPoint(id, collectionPoint);
        if (message.equals("Collection point updated successfully")){
            return new ResponseEntity<>(message,HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);

        }
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteCollectionPoint(@PathVariable UUID id) {
        String message = collectionPointService.deleteCollectionPoint(id);
        if (message.equals("Collection point deleted successfully")){
            return new ResponseEntity<>(message, HttpStatus.OK);

        }
        else
        {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
}
