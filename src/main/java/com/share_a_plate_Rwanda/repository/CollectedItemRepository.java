package com.share_a_plate_Rwanda.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.share_a_plate_Rwanda.model.CollectedItem;

@Repository 
public interface CollectedItemRepository extends JpaRepository<CollectedItem,UUID> {
    
}
