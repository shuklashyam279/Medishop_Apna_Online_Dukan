package com.medishop.dto;

import jakarta.persistence.Entity;
import lombok.Data;

/**
 * Address class is used to store the address of customers and vendors.
 * 
 * @author Shyam Shukla
 */
@Entity
@Data
public class Address {

	/**
	 * The id of the address.
	 * @see #setId(int)
	 * @see #getId()
	 */
	private int id;
	
	/**
	 * The first line of the address.
	 * @see #setLine1(String)
	 * @see #getLine1()
	 */
	private String line1;
	
	/**
	 * The second line of the address.
	 * @see #setLine2(String)
	 * @see #getLine2()
	 */
	private String line2;
	
	/**
	 * The city of the address.
	 * @see #setCity(String)
	 * @see #getCity()
	 */
	private String city;
	
	/**
	 * The state of the address.
	 * @see #setState(String)
	 * @see #getState()
	 */
	private String state;
	
	/**
	 * The country of the address.
	 * @see #setCountry(String)
	 * @see #getCountry()
	 */
	private String country;
	
	/**
	 * The pincode of the address.
	 * @see #setPincode(String)
	 * @see #getPincode()
	 */
	private String pincode;
	
	/**
	 * A no-args constructor.
	 */
	public Address() {
		super();
	}
	
	/**
	 * A constructor that takes the city, state, and pincode as parameters.
	 * 
	 * @param city the city
	 * @param state the state
	 * @param pincode the pincode
	 */
	public Address(String city, String state, String pincode) {
		super();
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	
	/**
	 * A constructor that takes the line1, city, state, and pincode as parameters.
	 * 
	 * @param line1 the first line of the address
	 * @param city the city
	 * @param state the state
	 * @param pincode the pincode
	 */
	public Address(String line1, String city, String state, String pincode) {
		this(city, state, pincode);
		this.line1 = line1;
	}
	
	/**
	 * A constructor that takes the line1, line2, city, state, and pincode as
	 * parameters.
	 * 
	 * @param line1 the first line of the address
	 * @param line2 the second line of the address
	 * @param city the city
	 * @param state the state
	 * @param pincode the pincode
	 */
	public Address(String line1, String line2, String city, String state, String pincode) {
		this(line1, city, state, pincode);
		this.line2 = line2;
	}

}