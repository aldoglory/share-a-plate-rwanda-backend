package com.share_a_plate_Rwanda.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "collection_point")
public class CollectionPoint{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int pointNumber;
    private String name;

    public CollectionPoint(){

    }

    public UUID getId() {return id;}
    public void setId(UUID id) {this.id = id;}

    public int getPointNumber() {return pointNumber;}
    public void setPointNumber(int pointNumber) {this.pointNumber = pointNumber;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}