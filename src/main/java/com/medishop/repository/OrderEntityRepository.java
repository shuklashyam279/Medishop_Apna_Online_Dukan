package com.medishop.repository;

import com.medishop.dto.OrderEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderEntityRepository extends JpaRepository<OrderEntitiy, Long> {

}
