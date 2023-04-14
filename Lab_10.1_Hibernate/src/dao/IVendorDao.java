package dao;

import pojos.Vendor;

public interface IVendorDao {

	String registerVendor(Vendor vendor);

	String cancelVendor(String email);

}
