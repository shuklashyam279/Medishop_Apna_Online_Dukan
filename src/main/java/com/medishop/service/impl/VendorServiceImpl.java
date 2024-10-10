package com.medishop.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.medishop.dao.VendorDao;
import com.medishop.dto.Vendor;
import com.medishop.response.ResponseStructure;
import com.medishop.service.VendorService;

import jakarta.servlet.http.HttpSession;

/**
 * @author Mo Masood Ansari
 *
 */
@Service
public class VendorServiceImpl implements VendorService {

	private final HttpSession httpSession;
	private final VendorDao dao;

	@Autowired
	public VendorServiceImpl(HttpSession httpSession, VendorDao dao) {
		this.httpSession = httpSession;
		this.dao = dao;
	}

	@Override
	public ResponseStructure<Vendor> saveVendorService(Vendor vendor) {
		String email = vendor.getEmail();
		String password = vendor.getPassword();
		
		if (email == null || password == null) {
			return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), "Please enter a valid USERNAME and PASSWORD!!!", vendor);
		}
		
		dao.saveVendorDao(vendor);
		return ResponseStructure.createResponse(HttpStatus.CREATED.value(), "Data Inserted!!!", vendor);
	}

	@Override
	public ResponseStructure<Vendor> getVendorByIdService(int id) {
		return null;
	}

	@Override
	public ResponseStructure<Vendor> getVendorByEmailService(String email) {
		Vendor vendor = dao.getVendorByEmailDao(email);
		
		if (vendor != null) {
			return ResponseStructure.createResponse(HttpStatus.FOUND.value(), "Vendor found", vendor);
		} else {
			return ResponseStructure.createResponse(HttpStatus.NOT_FOUND.value(), "Vendor not found", null);
		}
	}

	@Override
	public List<Vendor> getVendorsService() {
		return Collections.emptyList();
	}

	@Override
	public ResponseStructure<List<Vendor>> updateVendorByEmailService(Vendor vendor) {
		return null;
	}

	@Override
	public ResponseStructure<Vendor> deleteVendorByEmailService(String email) {
		return null;
	}

	@Override
	public ResponseStructure<Vendor> loginVendorByEmailAndPasswordService(String email, String password) {
		Vendor vendor = dao.getVendorByEmailDao(email);
		if (vendor != null) {
			if (vendor.getPassword().equals(password) && vendor.getVendorStatus().equalsIgnoreCase("active")) {
				httpSession.setAttribute("vendorEmail", email);
				vendor.setPassword("***********");
				return ResponseStructure.createResponse(HttpStatus.OK.value(), "Vendor logged in successfully", vendor);
			} else {
				return ResponseStructure.createResponse(HttpStatus.NOT_ACCEPTABLE.value(), 
					"Password is incorrect or vendor is not verified. Please contact admin.", vendor);
			}
		} else {
			Vendor dummyVendor = new Vendor();
			dummyVendor.setEmail(email);
			dummyVendor.setPassword("*********************");
			return ResponseStructure.createResponse(HttpStatus.NOT_FOUND.value(), "Vendor email is incorrect", dummyVendor);
		}
	}

	/**
	 * logout vendor from session
	 */
	@Override
	public ResponseEntity<String> logoutVendorService() {
		
		if(httpSession.getAttribute("vendorEmail")!=null) {
			httpSession.invalidate();
			return new ResponseEntity<>("vendor logout success", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Please Login Then Logout", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@Override
	public ResponseEntity<String> vendorVerifyByIdService(int id) {
		
		String adminEmail = (String) httpSession.getAttribute("adminEmail");
		
		if(adminEmail!=null){
			
			dao.vendorVerifyByIdDao(id);
			
			return new ResponseEntity<>("vendor Verified successfully", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Please Login with admin and then verify", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}