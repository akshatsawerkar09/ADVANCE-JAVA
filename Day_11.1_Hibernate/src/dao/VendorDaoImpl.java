package dao;

import static utils.HibernateUtils.getSf;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Location;
import pojos.Vendor;

public class VendorDaoImpl implements IVendorDao {

	@Override
	public String registerVendor(Vendor v) {

		String mesg = "Vendor reg failed";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			session.persist(v);

			tx.commit();

			mesg = "Vendor reg succeeded...";

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

	@Override
	public Vendor fetchVendorDetails(String email) {

		Vendor v = null;

		String jpql = "select v from Vendor v where v.email = :email";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			v = session.createQuery(jpql, Vendor.class).setParameter("email", email).getSingleResult();

			// here it will fetch only vendor details not location because uni-directional
			// approach which will help in performance
			// & also bank account also will not be fetched one to many lazy

			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return v;
	}

	@Override
	public Vendor fetchCompleteVendorDetails(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unsubscribeVendor(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String assignVendorLocation(int vendorId, Location location) {

		String mesg = "Location assignmnet failed";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			// get vendor details from vendor id
			Vendor vendor = session.get(Vendor.class, vendorId);

			if (vendor != null) {
				// valid vendor id
				location.setCurrentVendor(vendor);// setting uni-dir between location to --> Vendor but disadvantage of
													// uni-directional approach we don't enjoy cascading we have to
													// explicitly save locations details
				// save location details
				session.persist(location);

				mesg = "Vendor " + vendor.getName() + " assigned the location..";
			}

			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

	@Override
	public String registerVendorWithLocation(Location location) {

		String mesg = "Vendor reg failed";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			session.persist(location.getCurrentVendor());

			session.persist(location);

			tx.commit();

			mesg = "Vendor reg succeeded";

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

	@Override
	public Vendor getVendorDetailsByPhoneNo(String phoneNo) {

		Vendor v = null;

		// we cannot get phone number by jpql

		String sql = "select * from vendors v, vendor_phones p where v.id = p.vendor_id and p.phone_no = :phone";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			v = session.createNativeQuery(sql, Vendor.class).setParameter("phone", phoneNo).getSingleResult();

			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return v;
	}

}
