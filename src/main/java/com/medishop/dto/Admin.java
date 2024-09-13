package com.medishop.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
* Admin class is used to store admin details.
* Admin can register multiple vendors and manage all the vendors registered.
* 
* @author Shyam Shukla
*/
@Entity
@Getter
@Setter
public class Admin {

    /**
     * To be used in Dev phase only.
     * Constructor to create Admin object with NO attributes
     * 
     */
    public Admin() {}
    
    /**
     * To be used in Dev phase only.
     * Constructor to create Admin object with least attributes
     * 
     * @param name     name of the Admin
     * @param email    email of the Admin
     * @param password password of the Admin
     */
    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor to create Admin object
     * 
     * @param name     name of the Admin
     * @param email    email of the Admin
     * @param password password of the Admin
     * @param phone    phone number of the Admin
     */
    public Admin(String name, String email, String password, long phone) {
        this(name, email, password);
        this.phone = phone;
    }

    /**
     * Constructor to create Admin object
     * 
     * @param name     name of the Admin
     * @param email    email of the Admin
     * @param password password of the Admin
     * @param phone    phone number of the Admin
     * @param address  address of the Admin
     */
    public Admin(String name, String email, String password, long phone, String address) {

        this(name, email, password, phone);
        this.address = address;
    }

    /**
     * Constructor to create Admin object
     * 
     * @param name     name of the Admin
     * @param email    email of the Admin
     * @param password password of the Admin
     * @param address  address of the Admin
     * @param phone    phone number of the Admin
     * @param adhar    adhar number of the Admin
     */
    public Admin(String name, String email, String password, long phone, String address, long adhar) {

        this(name, email, password, phone, address);
        this.adhar = adhar;
    }

    /**
     * Constructor to create Admin object
     * 
     * @param name     name of the Admin
     * @param email    email of the Admin
     * @param password password of the Admin
     * @param phone    phone number of the Admin
     * @param address  address of the Admin
     * @param adhar    adhar number of the Admin
     * @param dob      date of birth of the Admin
     */
    public Admin(String name, String email, String password, long phone, String address,
                    long adhar, LocalDate dob) {

        this(name, email, password, phone, address, adhar);
        this.dob = dob;
    }

    /**
     * Unique identifier for admin.
	 */
    @Id
	private int id;

    /**
     * Name of admin.
     */
    private String name;
    
    /**
     * Email of admin
     */
    @Column(unique = true, nullable = false)
	private String email;
    /**
     * Password of admin
     */
	private String password;
    /**
     * Address of admin
     */
    private String address;
    /**
     * Date of birth of admin
     */
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;
    /**
     * Phone number of admin
     */
    private long phone;
    /**
     * Adhar number of admin
     */
    private long adhar;

    /**
     * Profile picture of admin in bytes
     */
    @Transient
	@Lob
	private byte[] image;
	
	/**
	 * List of vendors registered by admin
	 */
	@OneToMany(mappedBy = "admin")
	@JsonIgnore
	private List<Vendor> vendors;

}