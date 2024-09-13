package com.medishop.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 * Entity class for customer
 * 
 * @author Shyam Shukla
 */
@Entity
@Data
// @XmlRootElement
public class Customer {

    /**
     * Constructor to create customer object with NO attributes
     * To be used in Dev phase only.
     */
    public Customer() {}

    /**
     * Constructor to create customer object with least attributes
     * 
     * @param name     name of the customer
     * @param email    email of the customer
     * @param password password of the customer
     */
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor to create customer object
     * 
     * @param name     name of the customer
     * @param email    email of the customer
     * @param password password of the customer
     * @param phone    phone number of the customer
     */
    public Customer(String name, String email, String password, long phone) {
        this(name, email, password);
        this.phone = phone;
    }

    /**
     * Constructor to create customer object
     * 
     * @param name     name of the customer
     * @param email    email of the customer
     * @param password password of the customer
     * @param phone    phone number of the customer
     * @param address  address of the customer
     */
    public Customer(String name, String email, String password, long phone, String address) {

        this(name, email, password, phone);
        this.address = address;
    }

    /**
     * Constructor to create customer object
     * 
     * @param name     name of the customer
     * @param email    email of the customer
     * @param password password of the customer
     * @param address  address of the customer
     * @param phone    phone number of the customer
     * @param adhar    adhar number of the customer
     */
    public Customer(String name, String email, String password, long phone, String address, long adhar) {

        this(name, email, password, phone, address);
        this.adhar = adhar;
    }

    /**
     * Constructor to create customer object
     * 
     * @param name     name of the customer
     * @param email    email of the customer
     * @param password password of the customer
     * @param phone    phone number of the customer
     * @param address  address of the customer
     * @param adhar    adhar number of the customer
     * @param dob      date of birth of the customer
     */
    public Customer(String name, String email, String password, long phone, String address,
                    long adhar, LocalDate dob) {

        this(name, email, password, phone, address, adhar);
        this.dob = dob;
    }

	/**
	 * Unique identifier for the customer
	 */
	@Id
	private int id;

	/**
	 * Name of the customer
	 */
	private String name;

	/**
	 * Date of birth of the customer
	 * 
	 * @see #setDob(LocalDate)
	 * @see #getDob()
	 */
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;

	/**
	 * Email address of the customer, unique and not null
	 * 
	 * @see #setEmail(String)
	 * @see #getEmail()
	 */
	@Column(unique = true, nullable = false)
	private String email;

	/**
	 * Password of the customer, length of 8
	 * 
	 * @see #setPassword(String)
	 * @see #getPassword()
	 */
	@Column(length = 8)
	private String password;

	/**
	 * Address of the customer
	 * 
	 * @see #setAddress(String)
	 * @see #getAddress()
	 */
	private String address;

	/**
	 * Phone number of the customer
	 * 
	 * @see #setPhone(long)
	 * @see #getPhone()
	 */
	private long phone;

	/**
	 * Adhar number of the customer, unique and nullable
	 * 
	 * @see #setAdhar(long)
	 * @see #getAdhar()
	 */
	@Column(unique = true, nullable = true, length = 12)
	private long adhar;

	/**
	 * Image of the customer, transient and lob
	 * 
	 * @see #setImage(byte[])
	 * @see #getImage()
	 */
	@Transient
	@Lob
	private byte[] image;

	/**
	 * List of medicines purchased by the customer, many to many
	 * 
	 * @see #setMedicines(List)
	 * @see #getMedicines()
	 */
	@ManyToMany
	private List<Medicine> medicines;

}