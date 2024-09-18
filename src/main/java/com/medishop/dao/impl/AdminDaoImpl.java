package com.medishop.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medishop.dao.AdminDao;
import com.medishop.dto.Admin;
import com.medishop.repository.AdminRepository;

@Repository
public class AdminDaoImpl implements AdminDao {

	private final AdminRepository adminRepository;

	@Autowired
	public AdminDaoImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public Admin loginAdminByEmailAndPasswordDao(Admin admin) {
		return adminRepository.findByEmail(admin.getEmail());
	}
}