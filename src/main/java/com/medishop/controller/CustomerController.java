package com.medishop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medishop.dto.Customer;
import com.medishop.response.ResponseStructure;
import com.medishop.service.CustomerService;

/**
 * @author Shyam Shukla
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
	private final CustomerService service;

	@Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping(value = "/saveCustomer")
    public ResponseStructure<Customer> saveCustomer(@RequestBody Customer customer) {
        return service.saveCustomerService(customer);
    }

    @GetMapping(value = "/loginCustomer/{email}/{password}")
    public ResponseStructure<Customer> loginCustomer(@PathVariable String email, @PathVariable String password) {
        return service.loginCustomerByEmailPasswordService(email, password);
    }
    
    @GetMapping(value = "/logoutCustomer")
    public ResponseEntity<String> logoutCustomer() {
        return service.logoutCustomer();
    }
}