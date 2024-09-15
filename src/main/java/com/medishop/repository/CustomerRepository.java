package com.medishop.repository;

import com.medishop.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Shyam Shukla
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByEmail(String email);
}
