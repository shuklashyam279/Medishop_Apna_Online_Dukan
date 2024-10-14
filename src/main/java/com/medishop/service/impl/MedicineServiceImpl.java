package com.medishop.service.impl;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medishop.dao.MedicineDao;
import com.medishop.dao.VendorDao;
import com.medishop.dto.Medicine;
import com.medishop.dto.Vendor;
import com.medishop.response.ResponseStructure;
import com.medishop.service.MedicineService;


import jakarta.servlet.http.HttpSession;

@Service
public class MedicineServiceImpl implements MedicineService {

	private final VendorDao vendorDao;
	private final HttpSession httpSession;
	private final MedicineDao medicineDao;

	@Autowired
	public MedicineServiceImpl(VendorDao vendorDao, 
							   HttpSession httpSession, 
							   MedicineDao medicineDao) {
		this.vendorDao = vendorDao;
		this.httpSession = httpSession;
		this.medicineDao = medicineDao;
	}

	@Override
	public ResponseStructure<Medicine> saveMedicineService(Medicine medicine) {
		
		String email = (String) httpSession.getAttribute("vendorEmail");
		
		if (email != null) {
			Vendor vendor = vendorDao.getVendorByEmailDao(email);
			medicine.setVendors(new HashSet<>(Arrays.asList(vendor)));
			Medicine savedMedicine = medicineDao.saveMedicineDao(medicine);
			
			if (savedMedicine != null) {
				return ResponseStructure.createResponse(HttpStatus.ACCEPTED.value(), "Medicine added", savedMedicine);
			} else {
				return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Data is not stored, check your code", null);
			}
		} else {
			return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Please login with vendor and then add medicine", null);
		}
	}

	@Override
	public ResponseStructure<List<Medicine>> getAllMedicineService() {
		List<Medicine> medicines = medicineDao.getAllMedicineDao();
		if (!medicines.isEmpty()) {
			return ResponseStructure.createResponse(HttpStatus.OK.value(), "Data----Found----", medicines);
		} else {
			return ResponseStructure.createResponse(HttpStatus.NOT_FOUND.value(), "Data is not found might be table is empty or check your code", medicines);
		}
	}

	@Override
	public ResponseEntity<String> verifyMedicineStatusByAdminService(int medicineId, int vendorId) {
	    String adminEmail = (String) httpSession.getAttribute("adminEmail");
	    
	    if (adminEmail == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body("Please login as admin to verify");
	    }
	    
	    Vendor vendor = vendorDao.getVendorByIdDao(vendorId);
	    
	    if (vendor == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("Vendor not found");
	    }
	    
	    Medicine medicine = vendor.getMedicines().stream()
	        .filter(m -> m.getId() == medicineId)
	        .findFirst()
	        .orElse(null);
	    
	    if (medicine == null) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .body("Medicine not found for this vendor");
	    }
	    
	    medicine.setAvailable(true);  // Set the medicine as available
	    boolean verified = medicineDao.verifyMedicineStatusByAdminDao(medicine);
	    
	    if (verified) {
	        return ResponseEntity.ok("Medicine verified and set as available");
	    } else {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("Failed to verify medicine");
	    }
	}

	@Override
	public ResponseStructure<List<Medicine>> getAllMedicineByNameService(String name) {
		String customerEmail = (String) httpSession.getAttribute("customerEmail");
		String vendorEmail = (String) httpSession.getAttribute("vendorEmail");
		String adminEmail = (String) httpSession.getAttribute("adminEmail");
		
		if (customerEmail != null || vendorEmail != null || adminEmail != null) {
			List<Medicine> medicines = medicineDao.getAllMedicineByNameDao(name);
			if (!medicines.isEmpty()) {
				return ResponseStructure.createResponse(
					HttpStatus.FOUND.value(),
					"Medicines found",
					medicines
				);
			} else {
				return ResponseStructure.createResponse(
					HttpStatus.NOT_FOUND.value(),
					"No medicines found with the given name",
					medicines
				);
			}
		} else {
			return ResponseStructure.createResponse(
				HttpStatus.UNAUTHORIZED.value(),
				"Please login and then search",
				null
			);
		}
	}

}