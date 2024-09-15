package com.medishop.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.medishop.dto.Customer;
import com.medishop.response.ResponseStructure;

/**
 * @author Shyam Shukla
 */
public interface CustomerService {

	public ResponseStructure<Customer> saveCustomerService(Customer customer);

	public ResponseStructure<Customer> getCustomerByIdService(int id);

	public ResponseStructure<Customer> getCustomerByEmailService(String email);

	public List<Customer> getCustomersService();

	public ResponseStructure<List<Customer>> updateCustomerByEmailService(Customer customer);

	public ResponseStructure<Customer> deleteCustomerByEmailService(String email);
	
	public ResponseStructure<Customer> loginCustomerByEmailPasswordService(String email,String password);
	
	public ResponseEntity<String> logoutCustomer();
}
