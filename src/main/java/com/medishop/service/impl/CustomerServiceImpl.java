package com.medishop.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medishop.dao.CustomerDao;
import com.medishop.dto.Customer;
import com.medishop.response.ResponseStructure;
import com.medishop.service.CustomerService;

import jakarta.servlet.http.HttpSession;

/**
 * @author Shyam Shukla
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final HttpSession httpSession;
    private final CustomerDao dao;

    @Autowired
    public CustomerServiceImpl(HttpSession httpSession, CustomerDao dao) {
        this.httpSession = httpSession;
        this.dao = dao;
    }


	@Override
	public ResponseStructure<Customer> saveCustomerService(Customer customer) {
		String email = customer.getEmail();
		String password = customer.getPassword();
		
		if (email == null) {
			return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Please check your email!!!!", customer);
		}
		
		if (password == null) {
			return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Please check your password!!!!", customer);
		}
		
		int age = LocalDate.now().getYear() - customer.getDob().getYear();
		if (age < 18) {
			return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "You are not eligible. Your age is less than 18", null);
		}
		
		dao.saveCustomerDao(customer);
		return ResponseStructure.createResponse(HttpStatus.CREATED.value(), "Data Inserted!!!!", customer);
	}

	@Override
	public ResponseStructure<Customer> getCustomerByIdService(int id) {
		return null;
	}

	@Override
	public ResponseStructure<Customer> getCustomerByEmailService(String email) {
		return null;
	}

	@Override
	public List<Customer> getCustomersService() {
		return Collections.emptyList();
	}

	@Override
	public ResponseStructure<List<Customer>> updateCustomerByEmailService(Customer customer) {
		return null;
	}

	@Override
	public ResponseStructure<Customer> deleteCustomerByEmailService(String email) {
		return null;
	}

	@Override
	public ResponseStructure<Customer> loginCustomerByEmailPasswordService(String email, String password) {
		
		Customer customer = dao.getCustomerByEmailDao(email);
		
		if (customer != null) {
			if (customer.getPassword().equals(password)) {
				httpSession.setAttribute("customerEmail", email);
				return ResponseStructure.createResponse(HttpStatus.ACCEPTED.value(), "Login successful", null);
			} else {
				return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Invalid Password", customer);
			}
		} else {
			return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Invalid Email", null);
		}
	}

	@Override
	public ResponseEntity<String> logoutCustomer() {
		
		if(httpSession.getAttribute("customerEmail")!=null) {
			httpSession.invalidate();
		}
		return new ResponseEntity<>("Logout---Success", HttpStatus.OK);
	}

}