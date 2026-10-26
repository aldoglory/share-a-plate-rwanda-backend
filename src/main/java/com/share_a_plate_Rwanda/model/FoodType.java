package com.share_a_plate_Rwanda.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_type")
public class FoodType{
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToMany(mappedBy= "foodTypes")
    @JsonIgnore
    private List<Donor> donors = new ArrayList<> ();

    public FoodType()
    {}
    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name){this.name = name;}

    public List <Donor> getDonors() {return donors;}
    public void setDonors(List<Donor> donors) {this.donors = donors;}
    
}