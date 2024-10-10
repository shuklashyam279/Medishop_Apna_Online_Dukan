package com.medishop.dao.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medishop.dao.OrderEntityDao;
import com.medishop.dto.OrderEntity;
import com.medishop.repository.OrderEntityRepository;

@Repository
public class OrderEntityDaoImpl implements OrderEntityDao {
	
	private final OrderEntityRepository entityRepository;

    @Autowired
    public OrderEntityDaoImpl(OrderEntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

	/**
	 * Saves an OrderEntitiy to the database.
	 *
	 * @param entitiy The OrderEntitiy object to be saved.
	 * @return The saved OrderEntitiy object.
	 */
	@Override
	public OrderEntity saveOrderEntitiyDao(OrderEntity entitiy) {
		return entityRepository.save(entitiy);
	}

	/**
	 * Retrieves an OrderEntitiy by its ID from the database.
	 *
	 * @param orderId The ID of the OrderEntitiy to retrieve.
	 * @return The OrderEntitiy object if found, null otherwise.
	 */
	@Override
	public OrderEntity getOrderEntitiyByIdDao(long orderId) {
		Optional<OrderEntity> optional = entityRepository.findById(orderId);
		return (optional.isPresent())?optional.get():null;
	}

}