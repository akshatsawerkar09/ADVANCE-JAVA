package dao;

import pojos.Vendor;
import static utils.HibernateUtils.getSf;
import org.hibernate.*;

public class VendorDaoImpl implements IVendorDao {

	@Override
	public String registerVendor(Vendor vendor) {

		String message = "Vendor Registration Failed!!!";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			session.persist(vendor);

			tx.commit();

			message = "Vendor Registered Successfully : " + vendor.getName();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}

		}

		return message;
	}

	@Override
	public String cancelVendor(String email) {

		String mesg = "Cancel Vendor Failed";

		String jpql = "select v from Vendor v where v.email = :em";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			Vendor vendor = session.createQuery(jpql, Vendor.class).setParameter("em", email).getSingleResult();

			session.delete(vendor);

			mesg = "Vendor Cancelled!!!!";

			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

}
