package com.medishop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medishop.dto.Admin;
import com.medishop.response.ResponseStructure;
import com.medishop.service.AdminService;
import com.medishop.service.MedicineService;
import com.medishop.service.VendorService;

@RestController
public class AdminController {

    private final AdminService adminService;
    private final VendorService vendorService;
    private final MedicineService medicineService;

    @Autowired
    public AdminController(AdminService adminService, VendorService vendorService, MedicineService medicineService) {
        this.adminService = adminService;
        this.vendorService = vendorService;
        this.medicineService = medicineService;
    }

	@GetMapping("/loginAdmin")
	public ResponseStructure<Admin> loginAdminByEmailAndPasswordController(@RequestBody Admin admin) {
		return adminService.loginAdminByEmailAndPasswordService(admin);
	}

	@RequestMapping("/logout")
	public ResponseEntity<String> logoutAdmincontroller() {
		return adminService.logoutAdminService();
	}

	@PostMapping(value = "/verifyVendor/{id}")
	public ResponseEntity<String> vendorVerifyByIdService(@PathVariable int id) {
		return vendorService.vendorVerifyByIdService(id);
	}

	@PutMapping(value = "/verifyMedicine/{medicineId}/{vendorId}")
	public ResponseEntity<String> verifyMedicineStatusByAdminController(@PathVariable int medicineId,@PathVariable int vendorId) {
		return medicineService.verifyMedicineStatusByAdminService(medicineId, vendorId);
	}
}