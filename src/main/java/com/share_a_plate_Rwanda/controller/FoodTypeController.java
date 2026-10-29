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

import com.share_a_plate_Rwanda.Service.FoodTypeService;
import com.share_a_plate_Rwanda.model.FoodType;

@RestController 
@RequestMapping ("api/food-types")
public class FoodTypeController {
    @Autowired 
    private FoodTypeService foodTypeService;
    @PostMapping ("/save")
    public ResponseEntity<String> saveFoodType(@RequestBody FoodType foodType){
        String message = foodTypeService.saveFoodType(foodType);
        if (message.equals("Food type saved successfully")) {
            return new ResponseEntity<>(message,HttpStatus.CREATED);

            
        }
        else{
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodType>> getAllFoodTypes() {
        return new ResponseEntity<>(foodTypeService.getAllFoodTypes(),HttpStatus.OK);
        
    }
    @GetMapping ("/{id}")
    public ResponseEntity<?> getFoodTypeById(@PathVariable UUID id) {
        Optional<FoodType> foodType = foodTypeService.getFoodTypeById(id);
        if (foodType.isPresent()) {
            return new ResponseEntity<>(foodType.get(),HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>("The food with that id does not exist",HttpStatus.NOT_FOUND);

        }
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<String> updateFoodType(@PathVariable UUID id , @RequestBody FoodType foodType) {
        String message = foodTypeService.UpdateFoodType(id, foodType);
        if (message.equals("Food type updated successfully")){
            return new ResponseEntity<>(message,HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFoodType(@PathVariable UUID id ) {
        String message = foodTypeService.deleteFoodType(id);
        if(message.equals("Food type deleted successfully")) {
            return new ResponseEntity<>(message,HttpStatus.OK);

        }
        else {
            return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
        }
    }
}
