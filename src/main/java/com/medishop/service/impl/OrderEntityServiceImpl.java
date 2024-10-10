package com.medishop.service.impl;

import java.time.LocalDate;
import java.util.Random;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.medishop.dao.CustomerDao;
import com.medishop.dao.MedicineDao;
import com.medishop.dao.OrderEntityDao;
import com.medishop.dto.Customer;
import com.medishop.dto.Medicine;
import com.medishop.dto.OrderEntity;
import com.medishop.response.ResponseStructure;
import com.medishop.service.OrderEntityService;

import jakarta.servlet.http.HttpSession;

@Service
public class OrderEntityServiceImpl implements OrderEntityService{
	
	private final HttpSession httpSession;
	private final OrderEntityDao entityDao;
	private final MedicineDao medicineDao;
	private final CustomerDao customerDao;
	private static final Random RANDOM = new Random();

	@Autowired
	public OrderEntityServiceImpl(HttpSession httpSession, OrderEntityDao entityDao, MedicineDao medicineDao, CustomerDao customerDao) {
		this.httpSession = httpSession;
		this.entityDao = entityDao;
		this.medicineDao = medicineDao;
		this.customerDao = customerDao;
	}

	@Override
	public ResponseStructure<OrderEntity> saveOrderEntitiyService(OrderEntity entitiy, int medicineId) {
		String customerEmail = (String) httpSession.getAttribute("customerEmail");
		
		if (customerEmail != null) {
			// Generate random 10 digits orderId
			long orderId = RANDOM.nextLong(9000000000L) + 1000000000L;
			Medicine medicine = medicineDao.getMedicineByIdDao(medicineId);
			Customer customer = customerDao.getCustomerByEmailDao(customerEmail);
			
			entitiy.setCustomer(customer);
			entitiy.setMedicine(medicine);
			entitiy.setId(orderId);
			entitiy.setOrderDate(LocalDate.now());
			
			// Calculate final amount
			entitiy.setTotalAmount(medicine.getPrice().multiply(BigDecimal.valueOf(entitiy.getQuantity())));
			
			// Date calculation
			entitiy.setEstimatedDeliveryDate(LocalDate.now().plusDays(4));
			
			entityDao.saveOrderEntitiyDao(entitiy);
			return ResponseStructure.createResponse(HttpStatus.OK.value(), "Order Placed Successfully", entitiy);
		} else {
			return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Please login and then order", null);
		}
	}

	@Override
	public ResponseStructure<OrderEntity> getOrderEntitiyByIdService(int orderId) {
		return null;
	}

	
}