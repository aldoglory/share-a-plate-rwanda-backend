package com.share_a_plate_Rwanda.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share_a_plate_Rwanda.model.Donor;
import com.share_a_plate_Rwanda.model.FoodType;
import com.share_a_plate_Rwanda.repository.DonorRepository;
import com.share_a_plate_Rwanda.repository.FoodTypeRepository;

import jakarta.transaction.Transactional;

@Service 
public class FoodTypeService {
    @Autowired  
    private FoodTypeRepository foodTypeRepository;

    @Autowired 
    private DonorRepository donorRepository;

    public String saveFoodType(FoodType foodType) {
        foodTypeRepository.save(foodType);
        return "Food saved successfully";

    }
    public List<FoodType> getAllFoodTypes(){
        return foodTypeRepository.findAll();
    }

    public Optional<FoodType> getFoodTypeById(UUID id){
        return foodTypeRepository.findById(id);

    }
    public String UpdateFoodType(UUID id, FoodType foodType){
        Optional<FoodType> existing = foodTypeRepository.findById(id);
        if (existing.isEmpty()) {
            return "The food type with the id does not  exist";

        }
        FoodType toUpdate = existing.get();
        toUpdate.setName(foodType.getName());
        foodTypeRepository.save(toUpdate);
        return "Food type updated sucessfully";
    }

    @Transactional 
    public String deleteFoodType(UUID id){
        Optional<FoodType> existing = foodTypeRepository.findById(id);
        if (existing.isEmpty()) {
            return "The food with that id does not exist";

            
        }
        FoodType foodType = existing.get();
        //remove food typefrom donor
        for(Donor donor : foodType.getDonors()){
            donor.getFoodTypes().remove(foodType);
            donorRepository.save(donor);

        }
        foodTypeRepository.delete(foodType);
        return "Food type deleted successfully";
    }
    public List<FoodType>getUnusedFoodTypes() {
        return foodTypeRepository.findUnusedFoodTypes();
    }

    
}
