package com.medishop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Medicine class represents details of medicines in the system.
 * It includes information such as id, name, expiry date, manufacturer, quantity, price, and description.
 *
 * @author Shyam Shukla
 */
@Entity
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Medicine name is required")
    @Column(nullable = false)
    private String name;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "expiry_date", nullable = false)
    @Future(message = "Expiry date must be in the future")
    private LocalDate expiryDate;

    @Column(name = "company_name", nullable = false)
    @NotBlank(message = "Manufacturer name is required")
    private String companyName;

    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;

    @DecimalMin(value = "0.01", inclusive = false, message = "Price must be greater than 0")
    @Column(nullable = false)
    private BigDecimal price;

    @Column(length = 245)
    private String description;

    /**
     * The availability status of the medicine in the store.
     * When true, the medicine can be ordered by customers.
     */
    @Column(name = "is_available")
    private boolean isAvailable = false;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    @JoinTable(
        name = "medicine_vendor",
        joinColumns = @JoinColumn(name = "medicine_id"),
        inverseJoinColumns = @JoinColumn(name = "vendor_id")
    )
    private Set<Vendor> vendors = new HashSet<>();

    @ManyToMany(mappedBy = "medicines")
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();

    public Medicine() {}

    public Medicine(String name, LocalDate expiryDate, String companyName, int quantity, double price, String description) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.companyName = companyName;
        this.quantity = quantity;
        this.price = BigDecimal.valueOf(price);
        this.description = description;
    }

    /**
     * Checks if the medicine is available.
     *
     * @return true if the medicine is available, false otherwise
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the medicine.
     *
     * @param isAvailable the availability status to set
     */
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Other getters and setters are provided by Lombok's @Data annotation
}