package com.medishop.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medishop.dto.Admin;
import com.medishop.dto.Vendor;
import com.medishop.dao.VendorDao;
import com.medishop.repository.AdminRepository;
import com.medishop.repository.VendorRepository;

import jakarta.servlet.http.HttpSession;


/**
 * @author Shyam Shukla
 *
 */
@Repository
class VendorDaoImpl implements VendorDao {

	private final VendorRepository vendorRepository;
	private final HttpSession httpSession;
	private final AdminRepository adminRepository;

	@Autowired
	public VendorDaoImpl(VendorRepository vendorRepository, HttpSession httpSession, AdminRepository adminRepository) {
		this.vendorRepository = vendorRepository;
		this.httpSession = httpSession;
		this.adminRepository = adminRepository;
	}
	
	/**
	 * Saves a new vendor to the database.
	 *
	 * @param vendor The vendor object to be saved.
	 * @return The saved vendor object.
	 */
	@Override
	public Vendor saveVendorDao(Vendor vendor) {
		return vendorRepository.save(vendor);
	}

	/**
	 * Retrieves a vendor by their ID.
	 *
	 * @param id The ID of the vendor to retrieve.
	 * @return The vendor object if found, null otherwise.
	 */
	@Override
	public Vendor getVendorByIdDao(int id) {
		Optional<Vendor> optional = vendorRepository.findById(id);
		return optional.orElse(null);
	}

	/**
	 * Retrieves a vendor by their email address.
	 *
	 * @param email The email address of the vendor to retrieve.
	 * @return The vendor object if found, null otherwise.
	 */
	@Override
	public Vendor getVendorByEmailDao(String email) {
		return vendorRepository.findByEmail(email);
	}

	/**
	 * Retrieves all vendors from the database.
	 *
	 * @return A list of all vendor objects.
	 */
	@Override
	public List<Vendor> getVendorsDao() {
		return vendorRepository.findAll();
	}

	/**
	 * Updates an existing vendor in the database.
	 *
	 * @param vendor The vendor object with updated information.
	 * @return The updated vendor object if found and updated, null otherwise.
	 */
	@Override
	public Vendor updateVendorByEmailDao(Vendor vendor) {
		Vendor existingVendor = getVendorByEmailDao(vendor.getEmail());
		if (existingVendor != null) {
			return vendorRepository.save(vendor);
		}
		return null;
	}

	/**
	 * Deletes a vendor from the database by their email address.
	 *
	 * @param email The email address of the vendor to delete.
	 * @return The deleted vendor object if found and deleted, null otherwise.
	 */
	@Override
	public Vendor deleteVendorByEmailDao(String email) {
		Vendor vendor = getVendorByEmailDao(email);
		if (vendor != null) {
			vendorRepository.delete(vendor);
			return vendor;
		}
		return null;
	}

	/**
	 * Verifies a vendor by their ID and sets their status to active.
	 *
	 * @param id The ID of the vendor to verify.
	 * @return The verified and updated vendor object if found, null otherwise.
	 */
	@Override
	public Vendor vendorVerifyByIdDao(int id) {
		String adminEmail = (String) httpSession.getAttribute("adminEmail");
		Vendor vendor = getVendorByIdDao(id);
		Admin admin = adminRepository.findByEmail(adminEmail);
		
		if (vendor != null) {
			vendor.setVendorStatus("active");
			vendor.setAdmin(admin);
			return vendorRepository.save(vendor);
		}
		return null;
	}
}
