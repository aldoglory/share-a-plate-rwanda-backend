package com.share_a_plate_Rwanda.model;


import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "recipient")
public class Recipient{
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String organisationName;

    private String contactName;
    @Column(unique = true)
    private String registrationNumber;

    private String phone;
    private String district;

    public Recipient(){

    }

    public UUID getId() {return id;}
    public void setId(UUID id ) {this.id = id;}

    public String getOrganasationName() {return organisationName;}
    public void setOrganisationName(String organisationName) {this.organisationName = organisationName;}

    public String getContactName() {return contactName;}
    public void setContactName(String contactName) {this.contactName = contactName;}

    public String getRegistrationNumber(){return registrationNumber;}
    public void setRegistrationNumber(String registrationNumber) {this.registrationNumber = registrationNumber;}

    public String getPhone(){return phone;}
    public void setPhone(String phone) {this.phone = phone;}

    public String getDistrict() {return district;}
    public void setDistrict(String district) {this.district = district;}
    
}