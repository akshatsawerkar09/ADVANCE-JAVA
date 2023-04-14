package dao;

import static utils.HibernateUtils.getSf;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.BankAccount;
import pojos.Vendor;

public class BankAccountDaoImpl implements IBankAccountDao {

	@Override
	public String openBankAccount(BankAccount bankAccount, int vendorId) {

		String mesg = "Account open failed";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			Vendor vendor = session.get(Vendor.class, vendorId);

			if (vendor != null) {

				vendor.addBankAccount(bankAccount);

			}

			tx.commit();

			mesg = "Vendor Registered Successfully";

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return mesg;
	}

	@Override
	public String closeAccount(int vendorId, String accountType) {

		String mesg = "Close Bank Account Failed";

		String jpql = "select b from BankAccount b where b.accountType = :act";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			Vendor vendor = session.get(Vendor.class, vendorId);

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
