package com.share_a_plate_Rwanda.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share_a_plate_Rwanda.model.DonationStatus;
import com.share_a_plate_Rwanda.repository.CollectedItemRepository;
import com.share_a_plate_Rwanda.repository.CollectionPointRepository;
import com.share_a_plate_Rwanda.repository.DonationRepository;
import com.share_a_plate_Rwanda.repository.DonorRepository;
import com.share_a_plate_Rwanda.repository.RecipientRepository;

@Service 
public class DashboardService {
    @Autowired 
    private RecipientRepository recipientRepository;
    @Autowired 
    private DonorRepository donorRepository;
    @Autowired 
    private CollectionPointRepository collectionPointRepository;
    @Autowired 
    private DonationRepository donationRepository;
    @Autowired 
    private CollectedItemRepository collectedItemRepository;

    public Map<String, Long> getSummary() {
        Map<String , Long> summary = new LinkedHashMap<>();
        summary.put("recipients", recipientRepository.count());
        summary.put("donors", donorRepository.count());
        summary.put("FreeCollectionPoints", (long) collectionPointRepository.findFreeCollectionPoints().size());
        summary.put("donationsToday",donationRepository.countByDonationDate(LocalDate.now()));
        summary.put("posted", donationRepository.countByStatus(DonationStatus.POSTED));
        summary.put("collectedItems",collectedItemRepository.count());
        return summary;

    }
    
}
