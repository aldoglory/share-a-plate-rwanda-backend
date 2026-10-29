package com.share_a_plate_Rwanda.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.share_a_plate_Rwanda.model.CollectionPoint;
@Repository 
public interface CollectionPointRepository extends JpaRepository<CollectionPoint, UUID> {

    //c2
    @Query ("SELECT l FROM CollectionPoint l WHERE NOT EXISTS (SELECT p FROM Donor p WHERE p.collectionPoint = l) ORDER BY l.pointNumber")
    List<CollectionPoint>findFreeCollectionPoints();
} 
