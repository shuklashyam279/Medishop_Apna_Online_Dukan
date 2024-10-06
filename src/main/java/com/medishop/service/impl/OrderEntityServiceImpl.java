package com.medishop.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.medishop.dao.CustomerDao;
import com.medishop.dao.MedicineDao;
import com.medishop.dao.OrderEntityDao;
import com.medishop.dto.Customer;
import com.medishop.dto.Medicine;
import com.medishop.dto.OrderEntitiy;
import com.medishop.response.ResponseStructure;
import com.medishop.service.OrderEntityService;

import jakarta.servlet.http.HttpSession;

@Service
public class OrderEntityServiceImpl implements OrderEntityService{
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private OrderEntityDao entityDao;
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ResponseStructure<OrderEntitiy> responseStructure;

	@Override
	public ResponseStructure<OrderEntitiy> saveOrderEntitiyService(OrderEntitiy entitiy,int medicineId) {
		
		String customerEmail = (String) httpSession.getAttribute("customerEmail");
		
		if(customerEmail!=null) {
			/*
			 * it will generate random 10 digits orderId
			 */
			long orderId = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;
			Medicine medicine = medicineDao.getMedicineByIdDao(medicineId);
			Customer customer = customerDao.getCustomerByEmailDao(customerEmail);
			entitiy.setCustomer(customer);
			entitiy.setMedicine(medicine);
			entitiy.setOrderId(orderId);
			entitiy.setOrderDate(LocalDate.now());
			/**
			 * this line will calculate final amount
			 */
			entitiy.setTotalAmmount(medicine.getPrice()*entitiy.getQuantity());
			/**
			 * date calculation
			 */
			entitiy.setEstimatedDeliveryDate(LocalDate.now().plusDays(4));
			if(entitiy.getCustomerDeliveryDate()==null) {
				entitiy.setCustomerDeliveryDate(entitiy.getEstimatedDeliveryDate());
			}
			entityDao.saveOrderEntitiyDao(entitiy);
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMsg("Order Placed SuccessFully");
			responseStructure.setData(entitiy);
		}else {
			responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("Please login and then order");
			responseStructure.setData(null);
		}
		return responseStructure;
	}

	@Override
	public ResponseStructure<OrderEntitiy> getOrderEntitiyByIdService(int orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}