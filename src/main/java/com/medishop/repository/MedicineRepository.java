package com.medishop.repository;

import java.util.List;
import com.medishop.dto.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Shyam Shukla
 */
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {

	public Medicine findById(int medicineId);
	
	public List<Medicine> findByName(String name);
}
