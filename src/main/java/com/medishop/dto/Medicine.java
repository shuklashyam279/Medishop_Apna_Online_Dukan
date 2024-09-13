package com.medishop.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

/**
     * Medicine class is used to store details of Medicines.
     * It consists of id, name, expiry date, Manufacture company name, quantity, price and description.
     *
     * @param id to store id of the medicine.
     * @param name to store name of the medicine.
     * @param expiryDate to store expiry date of the medicine.
     * @param companyName to store company name of the medicine.
     * @param quantity to store quantity of the medicine.
     * @param price to store price of the medicine.
     * @param description to store description of the medicine.
     * @param vendors to store vendors of the medicine.
     * 
     * @author Shyam Shukla
     */

@Entity
@Data
public class Medicine {

    
    public Medicine() {}

    public Medicine(String name, LocalDate expiryDate, String companyName, int quantity, double price,
            String description) {

        this.name = name;
        this.expiryDate = expiryDate;
        this.companyName = companyName;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }
    
    @Id
    private int id;
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "expiryDate")
    private LocalDate expiryDate;

    @Column(name = "companyName")
    private String companyName;

    private int quantity;
    private double price;

    @Column(length = 245)
    private String description;

    @Column(length = 8)
    private String medicineStatus = "inactive";

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vendor> vendors;

    @ManyToMany(mappedBy = "medicines")
    private List<Customer> customers;
}