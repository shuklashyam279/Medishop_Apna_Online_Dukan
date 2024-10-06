package com.medishop.dto;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * Address class is used to store the address of customers and vendors.
 * 
 * @author Shyam Shukla
 */
@Entity
@Data
public class Address {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address line 1 is required")
    @Size(max = 100, message = "Address line 1 must not exceed 100 characters")
    private String line1;

    @Size(max = 100, message = "Address line 2 must not exceed 100 characters")
    private String line2;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City name must not exceed 50 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State name must not exceed 50 characters")
    private String state;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country name must not exceed 50 characters")
    private String country;

    @NotBlank(message = "Pincode is required")
    @Pattern(regexp = "^\\d{6}$", message = "Pincode must be exactly 6 digits")
    private String pincode;
	
	/**
	 * Default constructor.
	 */
	public Address() {}
	
	/**
	 * Constructs an Address with essential fields.
	 * 
	 * @param city The city or town name
	 * @param state The state or province name
	 * @param country The country name
	 * @param pincode The postal or ZIP code
	 */
	public Address(String city, String state, String country, String pincode) {
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	
	/**
	 * Constructs an Address with line1 and essential fields.
	 * 
	 * @param line1 The first line of the address
	 * @param city The city or town name
	 * @param state The state or province name
	 * @param country The country name
	 * @param pincode The postal or ZIP code
	 */
	public Address(String line1, String city, String state, String country, String pincode) {
		this(city, state, country, pincode);
		this.line1 = line1;
	}
	
	/**
	 * Constructs a complete Address with all fields.
	 * 
	 * @param line1 The first line of the address
	 * @param line2 The second line of the address (if applicable)
	 * @param city The city or town name
	 * @param state The state or province name
	 * @param country The country name
	 * @param pincode The postal or ZIP code
	 */
	public Address(String line1, String line2, String city, String state, String country, String pincode) {
		this(line1, city, state, country, pincode);
		this.line2 = line2;
	}

	public void validate() {
        if (line1 == null || line1.trim().isEmpty()) {
            throw new IllegalArgumentException("Address line 1 is required");
        }
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City is required");
        }
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("State is required");
        }
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country is required");
        }
        if (pincode == null || !pincode.matches("^\\d{6}$")) {
            throw new IllegalArgumentException("Pincode must be exactly 6 digits");
        }
    }

}