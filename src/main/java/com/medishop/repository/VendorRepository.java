package com.medishop.repository;

import com.medishop.dto.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Shyam Shukla
 */
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	public Vendor findByEmail(String email);
}
