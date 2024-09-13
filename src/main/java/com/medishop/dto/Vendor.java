package com.medishop.dto;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * @author Shyam Shukla
 */

@Entity
@Data
public class Vendor {

    @Id
    private int id;
    private String name;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(unique = true, nullable = true, length = 12)
    private long adhar;
    
    private long phone;
    private String password;
    private String address;

    @Column(name = "vendor_status")
    private String vendorStatus = "inactive";

    @ManyToMany
    private List<Customer> customers;

    @ManyToMany(mappedBy = "vendors")
    private List<Medicine> medicines;

    @ManyToOne
    private Admin admin;
}