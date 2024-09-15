package com.medishop.dao;

import com.medishop.dto.OrderEntitiy;

/**
 * @author Shyam Shukla
 */
public interface OrderEntityDao {

	public OrderEntitiy saveOrderEntitiyDao(OrderEntitiy entitiy);
	public OrderEntitiy getOrderEntitiyByIdDao(long orderId);
}
