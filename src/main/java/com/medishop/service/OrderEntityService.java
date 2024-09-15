package com.medishop.service;

import com.medishop.dto.OrderEntitiy;
import com.medishop.response.ResponseStructure;

/**
 * @author Shyam Shukla
 */
public interface OrderEntityService {

	public ResponseStructure<OrderEntitiy> saveOrderEntitiyService(OrderEntitiy entitiy,int medicineId);

	public ResponseStructure<OrderEntitiy> getOrderEntitiyByIdService(int orderId);
}