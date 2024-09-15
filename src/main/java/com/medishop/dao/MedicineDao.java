package com.medishop.dao;

import java.util.List;
import com.medishop.dto.Medicine;

/**
 * @author Shyam Shukla
 */
public interface MedicineDao {

	public Medicine saveMedicineDao(Medicine medicine);
	
	public List<Medicine> getAllMedicineDao();
	
	public boolean verifyMedicineStatusByAdminDao(Medicine medicine);
	
	public List<Medicine> getAllMedicineByNameDao(String name);
	
	public Medicine getMedicineByIdDao(int medicineId);
}