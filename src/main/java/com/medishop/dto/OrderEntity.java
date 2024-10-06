package com.medishop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * Represents an order in the MediShop system.
 * This entity contains all relevant information about a customer's order,
 * including dates, status, amount, and references to related entities.
 * 
 * @author Shyam Shukla
 * @version 1.1
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

	/**
	 * Unique identifier for the order. Automatically generated.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Date when the order was placed. Automatically set to the current date if not provided.
	 */
	@NotNull(message = "Order date is required")
	@PastOrPresent(message = "Order date must be in the past or present")
	@Column(nullable = false)
	private LocalDate orderDate;

	/**
	 * Estimated date of delivery. Must be in the future.
	 */
	@Future(message = "Estimated delivery date must be in the future")
	private LocalDate estimatedDeliveryDate;
	
	/**
	 * Actual date of delivery. Can be null if not yet delivered.
	 */
	@PastOrPresent(message = "Actual delivery date must be in the past or present")
	private LocalDate actualDeliveryDate;
	
	/**
	 * Current status of the order. Defaults to PENDING if not specified.
	 */
	@NotNull(message = "Order status is required")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private OrderStatus status;
	
	/**
	 * Total amount of the order. Must be positive and have at most 2 decimal places.
	 */
	@NotNull(message = "Total amount is required")
	@Positive(message = "Total amount must be positive")
	@Digits(integer = 10, fraction = 2, message = "Total amount must have at most 2 decimal places")
	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal totalAmount;
	
	/**
	 * Delivery address for the order.
	 */
	@NotNull(message = "Delivery address is required")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false)
	private Address deliveryAddress;
	
	/**
	 * Mode of payment used for the order.
	 */
	@NotNull(message = "Payment mode is required")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private PaymentMode paymentMode;
	
	/**
	 * Quantity of items in the order. Must be positive.
	 */
	@Positive(message = "Quantity must be positive")
	@Max(value = 1000, message = "Quantity cannot exceed 1000")
	@Column(nullable = false)
	private int quantity;
	
	/**
	 * Vendor supplying the order.
	 */
	@NotNull(message = "Vendor is required")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id", nullable = false)
	private Vendor vendor;
	
	/**
	 * Medicine being ordered.
	 */
	@NotNull(message = "Medicine is required")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medicine_id", nullable = false)
	private Medicine medicine;
	
	/**
	 * Customer placing the order.
	 */
	@NotNull(message = "Customer is required")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	/**
	 * Sets default values for orderDate and status before persisting.
	 */
	@PrePersist
	protected void onCreate() {
		if (orderDate == null) {
			orderDate = LocalDate.now();
		}
		if (status == null) {
			status = OrderStatus.PENDING;
		}
	}
}

/**
 * Represents the possible statuses of an order.
 */
enum OrderStatus {
	PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED
}

/**
 * Represents the possible payment modes for an order.
 */
enum PaymentMode {
	CASH, CREDIT_CARD, DEBIT_CARD, NET_BANKING, UPI
}