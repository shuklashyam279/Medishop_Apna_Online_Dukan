package com.medishop.dao;

import com.medishop.dto.OrderEntity;

/**
 * @author Shyam Shukla
 */
public interface OrderEntityDao {

	public OrderEntity saveOrderEntitiyDao(OrderEntity entitiy);
	public OrderEntity getOrderEntitiyByIdDao(long orderId);
}
