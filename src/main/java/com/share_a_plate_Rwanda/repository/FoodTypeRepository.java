package com.share_a_plate_Rwanda.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.share_a_plate_Rwanda.model.FoodType;

@Repository 
public interface FoodTypeRepository extends JpaRepository<FoodType,UUID> {

    //c3
    @Query("SELECT ft FROM FoodType ft WHERE ft.donors IS EMPTY")
    List<FoodType> findUnusedFoodTypes();
} 

    
