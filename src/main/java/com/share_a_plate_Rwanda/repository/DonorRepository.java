package com.share_a_plate_Rwanda.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.share_a_plate_Rwanda.model.Donor;

@Repository 
public interface DonorRepository extends JpaRepository<Donor, UUID> {
    //c1
    @Query ("SELECT DISTINCT d FROM Donor d JOIN d.foodTypes f " + "WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%')) ")
    List<Donor> findDonorsByFoodTypeName(@Param("name")String name);
    
}
