package dao;

import pojos.Location;
import pojos.Vendor;

public interface IVendorDao {

	String registerVendor(Vendor v);

	Vendor fetchVendorDetails(String email);

	Vendor fetchCompleteVendorDetails(String email);

	String unsubscribeVendor(String email);

	String assignVendorLocation(int vendorId, Location location);

	String registerVendorWithLocation(Location location);

	Vendor getVendorDetailsByPhoneNo(String phoneNo);

}
