package com.medishop.service.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.medishop.dao.AdminDao;
import com.medishop.dto.Admin;
import com.medishop.service.AdminService;
import com.medishop.response.ResponseStructure;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDao adminDao;
	private final HttpSession httpSession;

	@Autowired
	public AdminServiceImpl(HttpSession httpSession, AdminDao adminDao) {
		this.httpSession = httpSession;
		this.adminDao = adminDao;
	}

	@Override
	public ResponseStructure<Admin> loginAdminByEmailAndPasswordService(Admin admin) {

		Admin admin2 = adminDao.loginAdminByEmailAndPasswordDao(admin);

		if (admin2 != null) {
			if (admin2.getPassword().equals(admin.getPassword())) {
				httpSession.setAttribute("adminEmail", admin2.getEmail());
				admin2.setPassword("**********");
				return ResponseStructure.createResponse(HttpStatus.OK.value(), "Admin login successfully", admin2);
			} else {
				admin.setPassword("************");
				return ResponseStructure.createResponse(HttpStatus.NOT_FOUND.value(), "Invalid Password. Please check", admin);
			}
		} else {
			admin.setPassword("************");
			return ResponseStructure.createResponse(HttpStatus.NOT_FOUND.value(), "Invalid Email. Please check", admin);
		}
	}

	@Override
	public ResponseEntity<String> logoutAdminService() {

		if (httpSession.getAttribute("adminEmail") != null) {
			httpSession.invalidate();
			return new ResponseEntity<>("Logout---SuccessFully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("first login then logout", HttpStatus.OK);
		}
	}

}