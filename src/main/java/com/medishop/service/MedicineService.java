package com.medishop.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.medishop.dto.Medicine;
import com.medishop.response.ResponseStructure;

/**
 * @author Shyam Shukla
 */
public interface MedicineService {
	public ResponseStructure<Medicine> saveMedicineService(Medicine medicine);
	public ResponseStructure<List<Medicine>> getAllMedicineService();
	public ResponseEntity<String>  verifyMedicineStatusByAdminService(int medicineId,int vendorId);
	public ResponseStructure<List<Medicine>>  getAllMedicineByNameService(String name);
}