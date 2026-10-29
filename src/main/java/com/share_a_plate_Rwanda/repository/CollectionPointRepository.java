package com.share_a_plate_Rwanda.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.share_a_plate_Rwanda.model.CollectionPoint;
@Repository 
public interface CollectionPointRepository extends JpaRepository<CollectionPoint, UUID> {

    
} 
