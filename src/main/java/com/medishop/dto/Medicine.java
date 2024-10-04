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

    /**
     * No-argument constructor for Medicine.
     * To be used in Dev phase only.
     */
    public Medicine() {}

    /**
     * Constructor for Medicine with all the parameters.
     *
     * @param name        the name of the medicine
     * @param expiryDate  the expiry date of the medicine
     * @param companyName the company name of the medicine
     * @param quantity    the quantity of the medicine
     * @param price       the price of the medicine
     * @param description the description of the medicine
     */
    public Medicine(String name, LocalDate expiryDate, String companyName, int quantity, double price,
            String description) {

        this.name = name;
        this.expiryDate = expiryDate;
        this.companyName = companyName;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    /**
     * The id of the medicine.
     */
    @Id
    private int id;

    /**
     * The name of the medicine.
     */
    private String name;

    /**
     * The expiry date of the medicine.
     * 
     * @see #setExpiryDate(LocalDate)
     * @see #getExpiryDate()
     */
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "expiryDate")
    private LocalDate expiryDate;

    /**
     * The company name of the medicine.
     * 
     * @see #setCompanyName(String)
     * @see #getCompanyName()
     */
    @Column(name = "companyName")
    private String companyName;

    /**
     * The quantity of the medicine.
     * 
     * @see #setQuantity(int)
     * @see #getQuantity()
     */
    private int quantity;

    /**
     * The price of the medicine.
     * 
     * @see #setPrice(double)
     * @see #getPrice()
     */
    private double price;

    /**
     * The description of the medicine.
     * 
     * @see #setDescription(String)
     * @see #getDescription()
     */
    @Column(length = 245)
    private String description;

    /**
     * The availability status of the medicine in the store. Possible values are
     * false or true. When the medicineAvilabilityStatus is true, it can be ordered by
     * customers.
     * 
     * @see #setMedicineAvilability(boolean)
     * @see #getMedicineAvilability()
     */
    private boolean medicineAvilability = false;

    /**
     * Gets the availability status of the medicine.
     *
     * @return true if the medicine is available, false otherwise
     */
    public boolean getMedicineAvilability() {
        return medicineAvilability;
    }



    /**
     * The vendors list of the medicine.
     * 
     * @see #setVendors(List)
     * @see #getVendors()
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vendor> vendors;

    /**
     * The customers list of the medicine.
     * 
     * @see #setCustomers(List)
     * @see #getCustomers()
     */
    @ManyToMany(mappedBy = "medicines")
    private List<Customer> customers;
}