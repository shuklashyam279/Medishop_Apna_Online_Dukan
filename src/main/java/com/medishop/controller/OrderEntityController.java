package com.medishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.medishop.dto.OrderEntitiy;
import com.medishop.response.ResponseStructure;
import com.medishop.service.OrderEntityService;

@RestController
public class OrderEntityController {

	private final OrderEntityService entityService;
	
	@Autowired
	public OrderEntityController(OrderEntityService entityService) {
		this.entityService = entityService;
	}

	@PostMapping(value = "/saveOrder/{medicineId}")
	public ResponseStructure<OrderEntitiy> saveOrderEntitiyController(@RequestBody OrderEntitiy entitiy,@PathVariable int medicineId) {
		
		return entityService.saveOrderEntitiyService(entitiy, medicineId);
	}
}