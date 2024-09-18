package com.medishop.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.medishop.dto.Customer;
import com.medishop.response.ResponseStructure;
import com.medishop.service.CustomerService;

/**
 * @author Shyam Shukla
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    public CustomerController(CustomerService service) {
        this.service = service;
    }
    private final CustomerService service;

    @Autowired
	@PostMapping(value = "/saveCustomer")
	public ResponseStructure<Customer> saveCustomerController(@RequestBody Customer customer) throws IOException {
//		System.out.println("image wala controller!!!!!!!!!!!");
//		customer.setImage(multipartFile.getBytes());
		return service.saveCustomerService(customer);
	}

	@GetMapping(value = "/loginCustomer/{email}/{password}")
	public ResponseStructure<Customer> loginCustomerByEmailPasswordController(@PathVariable String email,@PathVariable String password) {
		return service.loginCustomerByEmailPasswordService(email, password);
	}
	
	@GetMapping(value = "/logoutCutomer")
	public ResponseEntity<String> logoutCustomerController() {
		return service.logoutCustomer();
	}
}