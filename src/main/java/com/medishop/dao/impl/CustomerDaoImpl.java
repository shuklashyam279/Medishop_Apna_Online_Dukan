package com.medishop.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medishop.dao.CustomerDao;
import com.medishop.dto.Customer;
import com.medishop.repository.CustomerRepository;

/**
 * @author Shyam Shukla
 */
@Repository
public class CustomerDaoImpl implements CustomerDao {

	private final CustomerRepository customerRepository;

    @Autowired
    public CustomerDaoImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

	public Customer saveCustomerDao(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerByIdDao(int id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public Customer getCustomerByEmailDao(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public List<Customer> getCustomersDao() {
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomerByEmailDao(Customer customer) {
		Customer customer2 = getCustomerByEmailDao(customer.getEmail());
		if (customer2 != null) {
			return customerRepository.save(customer);
		}
		return null;
	}

	@Override
	public Customer deleteCustomerByEmailDao(String email) {
		Customer customer = getCustomerByEmailDao(email);
		if (customer != null) {
			customerRepository.delete(customer);
			return null;
		}
		return customer;
	}

}