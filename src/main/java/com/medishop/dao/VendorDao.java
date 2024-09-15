package com.medishop.dao;

import java.util.List;
import com.medishop.dto.Vendor;


/**
 * @author Shyam Shukla
 *
 */
public interface VendorDao {

	public Vendor saveVendorDao(Vendor vendor);

	public Vendor getVendorByIdDao(int id);

	public Vendor getVendorByEmailDao(String email);

	public List<Vendor> getVendorsDao();

	public Vendor updateVendorByEmailDao(Vendor vendor);

	public Vendor deleteVendorByEmailDao(String email);
	
	public Vendor vendorVerifyByIdDao(int id);

}
