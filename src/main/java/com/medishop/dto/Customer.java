package com.medishop.dto;

import lombok.Data;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.*;

import java.util.Set;
import java.util.HashSet;

/**
 * Entity class for customer
 * 
 * @author Shyam Shukla
 */
// @XmlRootElement
@Entity
@Table(name = "customers")
@Data
public class Customer {

    public Customer() {}

    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Customer(String name, String email, String password, long phone) {
        this(name, email, password);
        this.phone = phone;
    }

    public Customer(String name, String email, String password, long phone, long adhar) {
        this(name, email, password, phone);
        this.adhar = adhar;
    }

    public Customer(String name, String email, String password, long phone, long adhar, LocalDate dob) {
        this(name, email, password, phone, adhar);
        this.dob = dob;
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@JsonFormat(pattern = "dd-MM-yyyy")
	@Past(message = "Date of birth must be in the past")
	private LocalDate dob;

	@Column(unique = true, nullable = false)
	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@Column(length = 60)  // Increased length for hashed passwords
	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 60, message = "Password must be between 8 and 60 characters")
	private String password;

	@NotNull(message = "Address is required")
	@Embedded
	private Address address;

	@Positive(message = "Phone number must be positive")
	@Digits(integer = 15, fraction = 0, message = "Invalid phone number")
	private long phone;

	@Column(unique = true, nullable = true)
	@Positive(message = "Adhar number must be positive")
	@Digits(integer = 12, fraction = 0, message = "Adhar number must be 12 digits")
	private long adhar;

	@Transient
	@Lob
	private byte[] image;

	@ManyToMany
	@JoinTable(
		name = "customer_medicines",
		joinColumns = @JoinColumn(name = "customer_id"),
		inverseJoinColumns = @JoinColumn(name = "medicine_id")
	)
	private Set<Medicine> medicines = new HashSet<>();

	private Set<Vendor> vendors = new HashSet<>();

	public Set<Vendor> getVendors() {
		return vendors;
	}
}