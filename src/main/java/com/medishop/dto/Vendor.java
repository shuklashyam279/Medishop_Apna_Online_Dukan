package com.medishop.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Represents a vendor in the MediShop system.
 * This entity contains all relevant information about a vendor,
 * including personal details, contact information, and relationships with other entities.
 *
 * @author Shyam Shukla
 * @version 1.2
 */
@Entity
@Table(name = "vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vendor {

    /**
     * Unique identifier for the vendor. Automatically generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Name of the vendor. Must be between 2 and 100 characters.
     */
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;
    
    /**
     * Email address of the vendor. Must be unique and valid.
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String email;
    
    /**
     * Adhar number of the vendor. Must be a 12-digit number if provided.
     */
    @Digits(integer = 12, fraction = 0, message = "Adhar number must be a 12-digit number")
    @Column(unique = true, nullable = true)
    private long adhar;
    
    /**
     * Phone number of the vendor. Must be a valid number.
     */
    @NotNull(message = "Phone number is required")
    @Positive(message = "Phone number must be positive")
    @Digits(integer = 15, fraction = 0, message = "Phone number must be at most 15 digits")
    @Column(nullable = false)
    private long phone;

    /**
     * Password for vendor account. Must be at least 8 characters long.
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Column(nullable = false)
    private String password;

    /**
     * Address of the vendor.
     */
    @NotBlank(message = "Address is required")
    @Column(nullable = false)
    private String address;

    /**
     * Status of the vendor. Defaults to "inactive".
     */
    @NotNull(message = "Vendor status is required")
    @Pattern(regexp = "^(active|inactive)$", message = "Vendor status must be either 'active' or 'inactive'")
    @Column(name = "vendor_status", nullable = false)
    @Builder.Default
    private String vendorStatus = "inactive";

    /**
     * List of customers associated with this vendor.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "vendor_customers",
        joinColumns = @JoinColumn(name = "vendor_id"),
        inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    @Builder.Default
    private List<Customer> customers = new ArrayList<>();

    /**
     * List of medicines supplied by this vendor.
     */
    @ManyToMany(mappedBy = "vendors", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Medicine> medicines = new ArrayList<>();

    /**
     * Admin who manages this vendor.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    /**
     * Adds a customer to the vendor's list of customers.
     *
     * @param customer The customer to add
     */
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.getVendors().add(this);
    }

    /**
     * Removes a customer from the vendor's list of customers.
     *
     * @param customer The customer to remove
     */
    public void removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.getVendors().remove(this);
    }

    /**
     * Adds a medicine to the vendor's list of medicines.
     *
     * @param medicine The medicine to add
     */
    public void addMedicine(Medicine medicine) {
        this.medicines.add(medicine);
        medicine.getVendors().add(this);
    }

    /**
     * Removes a medicine from the vendor's list of medicines.
     *
     * @param medicine The medicine to remove
     */
    public void removeMedicine(Medicine medicine) {
        this.medicines.remove(medicine);
        medicine.getVendors().remove(this);
    }
}