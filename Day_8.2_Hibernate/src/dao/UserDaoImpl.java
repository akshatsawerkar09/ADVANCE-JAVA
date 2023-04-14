package dao;

import pojos.User;
import static utils.HibernateUtils.getSf;

import org.hibernate.*;

public class UserDaoImpl implements IUserDao {

	@Override
	public String registerUser(User user) {

		// user : not part of l1 cache, not part of db only exist in heap : such a state
		// transient

		String mesg = "User Registration Failed!!!";

		// get session from session factory
		Session session = getSf().openSession(); // L1 cache is opened : empty

		// begin tx
		Transaction tx = session.beginTransaction(); // DB cn is pooled out n attached to hibernate session

		System.out.println("is open " + session.isOpen() + " is connected " + session.isConnected()); // t t

		try {

			// user registration
			// here we are passing transient pojo reference
			Integer id = (Integer) session.save(user); // Serializable is not integer, integer is serializable because
														// it return serializable

			tx.commit(); // in case of success commit tx

			mesg = "User Reged Successfully with ID " + id;

		} catch (RuntimeException e) {

			// roll back the tx and re- throw the exception to the caller
			if (tx != null) {
				tx.rollback();
			}

			throw e;

		} finally {
			// close the session
			if (session != null) {
				session.close();
			}
		}

		System.out.println("is open " + session.isOpen() + " is connected " + session.isConnected()); // f f

		return mesg;
	}

}
