package com.share_a_plate_Rwanda.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "donor")
public class Donor {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String businessName;
    private String contactName;
    private String phone;

    @ManyToOne
    @JoinColumn (name = "collection_point_id", nullable = true)
    private CollectionPoint collectionPoint;

    @ManyToMany
    @JoinTable (
        name = "donor_food_type", joinColumns = @JoinColumn(name = "donor_id"),
        inverseJoinColumns = @JoinColumn (name= "food_type_id")
    )
    private List<FoodType> foodTypes = new ArrayList<>();

    public Donor()
    {}
    public UUID getId() {return id;}
    public void setId(UUID id) {this.id= id;}

    public String getBusinessName() {return businessName;}
    public void setBusinessName(String businessName) {this.businessName = businessName;}

    public String getContactName() {return contactName;}
    public void setContactName(String contactName) {this.contactName= contactName;}
    
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public CollectionPoint getCollectionPoint() {return collectionPoint;}
    public void setCollectionPoint(CollectionPoint collectionPoint) {this.collectionPoint = collectionPoint;}
    
    public List<FoodType> getFoodTypes() {return foodTypes;}
    public void setFoodTypes(List<FoodType> foodTypes) {this.foodTypes= foodTypes;}
}