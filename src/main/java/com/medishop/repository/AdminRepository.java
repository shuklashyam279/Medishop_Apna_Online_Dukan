package com.medishop.repository;

import com.medishop.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Shyam Shukla
 */
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByEmail(String email);
}