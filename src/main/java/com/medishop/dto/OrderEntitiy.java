package com.medishop.dto;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Shyam Shukla
 */

@Entity
@Getter
@Setter
public class OrderEntitiy {

	@Id
	/**
	 * Unique identifier for the order
	 */
	private long orderId;
	
	/**
	 * Date when the order is estimated to be delivered
	 */
	private LocalDate estimatedDeliveryDate;
	
	/**
	 * Date when the order is delivered to the customer
	 */
	private LocalDate customerDeliveryDate;
	
	/**
	 * Date when the order is created
	 */
	private LocalDate orderDate;
	
	/**
	 * Status of the order. Default is "pending"
	 */
	private String orderStatus="pending";
	
	/**
	 * Total amount of the order
	 */
	private double totalAmmount;
	
	/**
	 * Address where the order is to be delivered
	 */
	private Address address;
	
	/**
	 * The mode of payment used for the order
	 */
	private String paymentMode;
	
	/**
	 * Quantity of the order
	 */
	private int quantity;
	
	/**
	 * Reference to the vendor who supplied the order
	 */
	@OneToOne
	private Vendor vendor;
	
	/**
	 * Reference to the medicine ordered
	 */
	@OneToOne
	private Medicine medicine;
	
	/**
	 * Reference to the customer who made the order
	 */
	@OneToOne
	private Customer customer;
}