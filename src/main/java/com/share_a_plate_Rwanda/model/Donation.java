package com.share_a_plate_Rwanda.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="donation")
public class Donation{
    @Id 
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;
    private LocalDate donationDate;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;

    private String foodDescription;

    private int estimatedPortions;

    @ManyToOne
    @JoinColumn (name= "recipient_id")
    private Recipient recipient;

    @ManyToOne
    @JoinColumn(name="donor_id")
    private Donor donor;
    public Donation(){}

    public UUID getId() {return id;}
    public void setId(UUID id ) {this.id = id;}

    public LocalDate getDonationDate(){return donationDate;}
    public void setDonationDate(LocalDate donationDate) {this.donationDate = donationDate;}

    public DonationStatus geStatus() {return status;}
    public void setStatus(DonationStatus status ) {this.status = status;}

    public String getFoodDescription() {return foodDescription;}
    public void setFoodDescription(String foodDescription) {this.foodDescription = foodDescription;}

    public int getEstimatedPortions() {return estimatedPortions;}
    public void setEstimatedPortions(int estimatedPortions) {this.estimatedPortions = estimatedPortions;}

    public Recipient gRecipient() {return recipient;}
    public void setRecipient(Recipient recipient) {this.recipient = recipient;}

    public Donor getDonor() {return donor;}
    public void setDonor(Donor donor ) {this.donor = donor;}
    


}