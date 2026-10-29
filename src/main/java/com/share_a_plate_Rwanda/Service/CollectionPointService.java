package com.share_a_plate_Rwanda.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share_a_plate_Rwanda.model.CollectionPoint;
import com.share_a_plate_Rwanda.repository.CollectionPointRepository;

@Service 
public class CollectionPointService {

    @Autowired 
    private CollectionPointRepository collectionPointRepository;

    public String saveCollectionPoint(CollectionPoint collectionPoint) {
        collectionPointRepository.save(collectionPoint);
       return "Collection point saved successfully";   
    }
    public List<CollectionPoint> getAllCollectionPoints() {
        return collectionPointRepository.findAll();
    }
    public Optional<CollectionPoint> getCollectionPointById(UUID id) {
        return collectionPointRepository.findById(id);
    }

    public String updateCollectionPoint(UUID id, CollectionPoint collectionPoint) {
        Optional<CollectionPoint> existing = collectionPointRepository.findById(id);
        if (existing.isEmpty()){
            return "The collection point with that id does not exist";

        }
        CollectionPoint toUpdate = existing.get();
        toUpdate.setPointNumber(collectionPoint.getPointNumber());
        toUpdate.setName(collectionPoint.getName());
        collectionPointRepository.save(toUpdate);
        return "Collection point updated successfully";

    }

    public String deleteCollectionPoint (UUID id ) {
        if (!collectionPointRepository.existsById(id)){
            return "The collection point with that id does not exist";

        }

        collectionPointRepository.deleteById(id);
        return "Collection point deleted successfully";
    }
            //c2
        public List<CollectionPoint> getFreeCollectionPoints(){
            return collectionPointRepository.findFreeCollectionPoints();
        }
}
