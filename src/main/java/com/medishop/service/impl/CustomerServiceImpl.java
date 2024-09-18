package com.medishop.service.impl;

import java.time.LocalDate;
import java.util.List;

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
    private final ResponseStructure<Customer> structure;
    private final ResponseStructure<List<Customer>> structure2;

    @Autowired
    public CustomerServiceImpl(HttpSession httpSession, CustomerDao dao, ResponseStructure<Customer> structure, 
								ResponseStructure<List<Customer>> structure2) {
        this.httpSession = httpSession;
        this.dao = dao;
        this.structure = structure;
        this.structure2 = structure2;
    }


	@Override
	public ResponseStructure<Customer> saveCustomerService(Customer customer) {
		String email = customer.getEmail();
		String password = customer.getPassword();
		if (email != null) {
			if (password != null) {
				int currentYear = LocalDate.now().getYear();
				int customerDobYear = customer.getDob().getYear();
				int age = currentYear - customerDobYear;
				if (age >= 18) {
					dao.saveCustomerDao(customer);
					structure.setData(customer);
					structure.setMsg("Data Inserted!!!!");
					structure.setStatus(HttpStatus.CREATED.value());
				} else {
					structure.setData(null);
					structure.setMsg("you are not eligible your age is less than 18");
					structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
				}

			} else {
				structure.setData(customer);
				structure.setMsg("Please check your password!!!!");
				structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			}
		} else {
			structure.setData(customer);
			structure.setMsg("Please check your email!!!!");
			structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		}
		return structure;
	}

	@Override
	public ResponseStructure<Customer> getCustomerByIdService(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseStructure<Customer> getCustomerByEmailService(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getCustomersService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseStructure<List<Customer>> updateCustomerByEmailService(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseStructure<Customer> deleteCustomerByEmailService(String email) {
		return null;
	}

	@Override
	public ResponseStructure<Customer> loginCustomerByEmailPasswordService(String email, String password) {
		
		Customer customer=dao.getCustomerByEmailDao(email);
		
		if(customer!=null) {
			
			if(customer.getPassword().equals(password)) {
				httpSession.setAttribute("customerEmail", email);
				structure.setMsg("login successfully");
				structure.setStatus(HttpStatus.ACCEPTED.value());
				structure.setData(null);
			}else {
				structure.setMsg("Invalid Password");
				structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
				structure.setData(customer);
			}
		}else {
			structure.setMsg("Invalid Email");
			structure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			structure.setData(customer);
		}
		return structure;
	}

	@Override
	public ResponseEntity<String> logoutCustomer() {
		
		if(httpSession.getAttribute("customerEmail")!=null) {
			httpSession.invalidate();
		}
		return new ResponseEntity<>("Logout---Success", HttpStatus.OK);
	}

}