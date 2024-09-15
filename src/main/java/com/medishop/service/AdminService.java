package com.medishop.service;

import org.springframework.http.ResponseEntity;

import com.medishop.dto.Admin;
import com.medishop.response.ResponseStructure;

/**
 * @author Shyam Shukla
 */
public interface AdminService {

	public ResponseStructure<Admin> loginAdminByEmailAndPasswordService(Admin admin);
	
	public ResponseEntity<String> logoutAdminService();
}
