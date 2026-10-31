package com.share_a_plate_Rwanda.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share_a_plate_Rwanda.model.CollectionPoint;
import com.share_a_plate_Rwanda.model.Donor;
import com.share_a_plate_Rwanda.model.FoodType;
import com.share_a_plate_Rwanda.repository.CollectionPointRepository;
import com.share_a_plate_Rwanda.repository.DonorRepository;
import com.share_a_plate_Rwanda.repository.FoodTypeRepository;

import jakarta.transaction.Transactional;

@Service 
public class DonorService {
    @Autowired 
    private DonorRepository donorRepository;
    @Autowired 
    private CollectionPointRepository collectionPointRepository;
    @Autowired 
    private FoodTypeRepository foodTypeRepository;
    
    public String saveDonor (Donor donor) {
        String check = attachRelations(donor, donor);
        if (check !=null) {
            return check;
            
        }
        donorRepository.save(donor);
        return "Donor saved successfully";

    }
    public List<Donor> getAllDonors(){
        return donorRepository.findAll();

    }
    public Optional<Donor> getDonorById(UUID id) {
    return donorRepository.findById(id);


}
public String updateDonor(UUID id, Donor donor){
    Optional<Donor> existing = donorRepository.findById(id);
    if (existing.isEmpty()) {
        return "The donor with id does not exist";

        
    }
    Donor toUpdate = existing.get();
    String check= attachRelations(donor,toUpdate);
    if (check != null) {
        return check;
    }
    toUpdate.setBusinessName(donor.getBusinessName());
    toUpdate.setContactName(donor.getContactName());
    toUpdate.setPhone(donor.getPhone());
    donorRepository.save (toUpdate);
    return "Donor updated successfully";
}

public String deleteDonor(UUID id){
    if (!donorRepository.existsById(id)) {
        return "Donor with that id does not exist";

    }
    donorRepository.deleteById(id);
    return "Donor deleted succesfully";

}
//d3
@Transactional 
public String addFoodTypeToDonor(UUID donorId, UUID foodTypeId) {
    Optional<Donor> donorOpt = donorRepository.findById(donorId);
    if(donorOpt.isEmpty()) {
        return "The donor with that id does not exist";
    }
    Optional<FoodType> foodTypeOpt = foodTypeRepository.findById(foodTypeId);
    if (foodTypeOpt.isEmpty()) {
        return "That food type does not exist";
        
    }
    Donor donor = donorOpt.get();
    FoodType foodType = foodTypeOpt.get();

if (donor.getFoodTypes().contains(foodType)){
    return "The donor already has that food type";
}
donor.getFoodTypes().add(foodType);
donorRepository.save(donor);
return "Food type added to the donor";}
//d4
public String changeCollectionPoint(UUID donorId, int pointNumber) {
    Optional<Donor>donorOpt = donorRepository.findById(donorId);
    if (donorOpt.isEmpty()) {
        return "The donor with that id does not exist";
        
    }
    Optional<CollectionPoint> pointOpt = collectionPointRepository.findByPointNumber(pointNumber);
    if(pointOpt.isEmpty()) {
        return "The collection point with that pointNumber does not exist";

    }
    Donor donor = donorOpt.get();
    CollectionPoint newPoint = pointOpt.get();

    boolean alreadyHere = donor.getCollectionPoint() !=null && donor.getCollectionPoint().getId().equals(newPoint.getId());
   if(!alreadyHere && donorRepository.existsByCollectionPoint(newPoint)){
    return "That collection point is taken by another donor";
   }

   donor.setCollectionPoint(newPoint);
   donorRepository.save(donor);
   return "Collection point changed successfully";
}
//c1
public List<Donor> getDonorsByFoodType(String name){
    return donorRepository.findDonorsByFoodTypeName(name);
}
//loading collection point an food types

private String attachRelations(Donor source, Donor target) {
    if (source.getCollectionPoint() != null && source.getCollectionPoint().getId() !=null) {
        Optional<CollectionPoint> point = collectionPointRepository.findById(source.getCollectionPoint().getId());
        if(point.isEmpty()) {
            return  "The collection point with that id does not exist";

        }
        target.setCollectionPoint(point.get());

    }
    else {

        target.setCollectionPoint(null);

    }
    List<FoodType> foodTypes = new ArrayList<>();
    if (source.getFoodTypes() != null) {
        for(FoodType ft : source.getFoodTypes()) {
            Optional<FoodType> found = foodTypeRepository.findById(ft.getId());
            if (found.isEmpty()) {
                return "The food type with that id does not exist";
                
            }
            foodTypes.add(found.get());
        }
        
    }
    target.setFoodTypes(foodTypes);
    return null;
}

}

