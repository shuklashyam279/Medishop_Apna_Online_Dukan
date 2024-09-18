package com.medishop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medishop.dto.Medicine;
import com.medishop.response.ResponseStructure;
import com.medishop.service.MedicineService;

@RestController
@RequestMapping(value = "/medicine")
public class MedicineController {

	private final MedicineService medicineService;

	@Autowired
	public MedicineController(MedicineService medicineService) {
		this.medicineService = medicineService;
	}

	@PostMapping(value = "/saveMedicine")
	public ResponseStructure<Medicine> saveMedicineController(@RequestBody Medicine medicine) {
		return medicineService.saveMedicineService(medicine);
	}

	@GetMapping(value = "/getAllMedicineByName/{name}")
	public ResponseStructure<List<Medicine>> getAllMedicineByNameDao(@PathVariable String name) {
		return medicineService.getAllMedicineByNameService(name);
	}
}