package com.share_a_plate_Rwanda.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "collected_item")
public class CollectedItem {
    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private String itemName;
    private String condition;
    private int portions;
    private LocalDate collectedDate;
    @ManyToOne 
    @JoinColumn(name= "donation_id")
    private Donation donation;

    public  CollectedItem(){}
    public UUID getId(){return id;}
    public void setId(UUID id) {this.id = id;}

    public String getItemName() {return itemName;}
    public void SetItemName(String itemName) {this.itemName = itemName;}

    public String getCondition() {return condition;}
    public void setCondition(String condition) {this.condition = condition;}

    public int getPortions() {return portions;}
    public void setPortions(int portions) {this.portions = portions;}
    
    public LocalDate getCollectedDate() {return collectedDate;}
    public void setCollectedDate(LocalDate collectedDate) {this.collectedDate = collectedDate;}

    public Donation getDonation() {return donation;}
    public void setDonation(Donation donation) {this.donation = donation;}
    
    
}
