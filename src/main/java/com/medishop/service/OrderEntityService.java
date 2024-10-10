package com.medishop.service;

import com.medishop.dto.OrderEntity;
import com.medishop.response.ResponseStructure;

/**
 * @author Shyam Shukla
 */
public interface OrderEntityService {

	public ResponseStructure<OrderEntity> saveOrderEntitiyService(OrderEntity entitiy,int medicineId);

	public ResponseStructure<OrderEntity> getOrderEntitiyByIdService(int orderId);
}