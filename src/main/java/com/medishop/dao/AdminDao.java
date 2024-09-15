package com.medishop.dao;

/**
 * @author Shyam Shukla
 */
import com.medishop.dto.Admin;

public interface AdminDao {

	public Admin loginAdminByEmailAndPasswordDao(Admin admin);
}
