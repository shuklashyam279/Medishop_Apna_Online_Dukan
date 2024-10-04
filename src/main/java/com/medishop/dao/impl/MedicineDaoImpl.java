package com.medishop.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medishop.dto.Medicine;
import com.medishop.dao.MedicineDao;
import com.medishop.repository.MedicineRepository;

@Repository
public class MedicineDaoImpl implements MedicineDao {

	private final MedicineRepository medicineRepository;

	@Autowired
	public MedicineDaoImpl(MedicineRepository medicineRepository) {
		this.medicineRepository = medicineRepository;
	}

	/**
	 * Saves a medicine to the database.
	 *
	 * @param medicine The medicine object to be saved.
	 * @return The saved medicine object.
	 */
	@Override
	public Medicine saveMedicineDao(Medicine medicine) {
		return medicineRepository.save(medicine);
	}

	/**
	 * Retrieves all medicines from the database.
	 *
	 * @return A list of all medicines.
	 */
	@Override
	public List<Medicine> getAllMedicineDao() {
		return medicineRepository.findAll();
	}

	/**
	 * Verifies the status of a medicine by admin.
	 *
	 * @param medicine The medicine object to be verified.
	 * @return true if the medicine is successfully saved after verification, false otherwise.
	 */
	@Override
	public boolean verifyMedicineStatusByAdminDao(Medicine medicine) {
		return medicineRepository.save(medicine) != null;
	}

	/**
	 * Retrieves all active medicines with a given name.
	 *
	 * @param name The name of the medicine to search for.
	 * @return A list of active medicines matching the given name.
	 */
	@Override
	public List<Medicine> getAllMedicineByNameDao(String name) {
		return medicineRepository.findByName(name).stream()
				.filter(Medicine::getMedicineAvilability)
				.collect(java.util.stream.Collectors.toList());
	}

	/**
	 * Retrieves a medicine by its ID.
	 *
	 * @param medicineId The ID of the medicine to retrieve.
	 * @return The medicine object if found, null otherwise.
	 */
	@Override
	public Medicine getMedicineByIdDao(int medicineId) {
		return medicineRepository.findById(medicineId);
	}

}
