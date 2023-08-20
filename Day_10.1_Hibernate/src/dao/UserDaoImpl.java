package dao;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Role;
import pojos.User;

public class UserDaoImpl implements IUserDao {

	@Override
	public String registerUser(User user) {

		// user : exist only in heap not in L1 cache not in DB : transient

		String mesg = "user registration failed...";

		// get session from SF
		Session session = getSf().openSession();

		Session session2 = getSf().openSession();

		System.out.println("Session same " + (session == session2)); // false

		// begin tx
		Transaction tx = session.beginTransaction(); // l1 cache i created : empty

		System.out
				.println("Is session open ? " + session.isOpen() + " is connected with DB cn " + session.isConnected()); // yes
																															// yes

		try {

			Integer id = (Integer) session.save(user); // here we pass transient pojo ref : in save user ref is simply
														// added to L1 cache : persistent : heap & l1 cache not part of
														// DB

			// success
			tx.commit(); // when we say HB perform automatic dirty checking @ commit : hibernate check
							// state of l1 cache and compare it with DB it will different : it ill fire
							// insert query

			System.out.println(
					"Is session open ? " + session.isOpen() + " is connected with DB cn " + session.isConnected()); // true
																													// true

			mesg = "User registered successfully, ID = " + id;

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close(); // l1 cache destroyed n db connection return to the pool, closing the session
			}
		}

		System.out
				.println("Is session open ?" + session.isOpen() + " is connected with DB cn " + session.isConnected()); // false
																														// false

		// user : detached

		return mesg;
	}

	@Override
	public String registerUserWithGetCurrentSession(User user) {

		String mesg = "User Registration Failed!!!";

		// get session from sessionFactory
		Session session = getSf().getCurrentSession();

		Session session2 = getSf().getCurrentSession();

		System.out.println(session == session2); // true

		// begin tx
		Transaction tx = session.beginTransaction();

		System.out
				.println("Is session open ? " + session.isOpen() + " is connected with DB cn " + session.isConnected()); // true
																															// true

		try {

			// Integer id = (Integer) session.save(user);
			session.persist(user);

			tx.commit(); // hibernate l1 cache destroyed and db return to the pool : insert query fired

			mesg = "User Registered Successfully, ID = " + user.getUserId();

			System.out.println(
					"Is session open ? " + session.isOpen() + " is connected with DB cn " + session.isConnected()); // false
																													// false

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback(); // session closed Db return the pool
			}
			System.out.println(
					"Is session open ? " + session.isOpen() + " is connected with DB cn " + session.isConnected()); // false
																													// false
			throw e;
		}

		return mesg;
	}

	@Override
	public User getUserDetails(int userId) {

		User u = null; // u : Not Applicable

		// get session from SF
		Session session = getSf().getCurrentSession();

		// begin tx
		Transaction tx = session.beginTransaction();

		try {

			// session API : public <T> get(Class<T> pojoClass, Serializable id)
			u = session.get(User.class, userId); // if we call this one time with correct id select will be fired one
													// time and if we give incorrect id then it will fire select query 3
													// times

			u = session.get(User.class, userId);

			u = session.get(User.class, userId); // valid id : persistent

			tx.commit();

		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return u; // u : detached : not part of l1 cache but part of DB
	}

	@Override
	public List<User> fetchAllUserDetails() {

		String jpql = "select u from User u"; // select * from users_tbl

		List<User> userList = null;

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			userList = session.createQuery(jpql, User.class).getResultList();

			// execute query - RST - processing of rst : user default - setters ref will be
			// added to l1 cache - will also added to list
			// method rets list of persistent pojos to the caller
			// the results of getResultList are not cached auto
			// you will have explicitly enable Query level cache

			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return userList;
	}

	@Override
	public List<User> fetchSelectedUserDetails(LocalDate start, LocalDate end, Role useRole) {

		List<User> users = null;

		String jpql = "select u from User u where u.regDate between :strt and :end and u.userRole = :rl";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			users = session.createQuery(jpql, User.class).setParameter("strt", start).setParameter("end", end)
					.setParameter("rl", useRole).getResultList();

			tx.commit(); // if we forget this we will get connection leak detected

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return users; // users : list of detached pojos
	}

	@Override
	public User authenticateUser(String email, String pwd) {

		String jpql = "select u from User u where u.email = :em and u.password = :pass";

		User u = null;

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			u = session.createQuery(jpql, User.class).setParameter("em", email).setParameter("pass", pwd)
					.getSingleResult();

			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return u;
	}

	@Override
	public List<String> fetchSelectedUserNames(LocalDate start, LocalDate end, Role useRole) {

		List<String> names = null;

		String jpql = "select u.name from User u where u.regDate between :strt and :end and u.userRole = :rl";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			names = session.createQuery(jpql, String.class).setParameter("strt", start).setParameter("end", end)
					.setParameter("rl", useRole).getResultList();

			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return names;
	}

	@Override
	public List<User> fetchSelectedUserDetailsConstrExpr(LocalDate start, LocalDate end, Role useRole) {

		List<User> users = null;

		String jpql = "select new pojos.User(name,regAmount,regDate) from User u where u.regDate between :strt and :end and u.userRole = :rl";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		try {

			users = session.createQuery(jpql, User.class).setParameter("strt", start).setParameter("end", end)
					.setParameter("rl", useRole).getResultList();

			tx.commit();

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		return users;
	}

	@Override
	public String changePassword(int userId, String newPwd) {

		String mesg = "Password updation failed!!!!";

		Session session = getSf().getCurrentSession();

		Transaction tx = session.beginTransaction();

		User u = null;

		try {

			// get user details by id
			u = session.get(User.class, userId);

			if (u != null) {
				// valid id u : persistent
				u.setPassword(newPwd); // here we are updating state of persistent pojo

				mesg = "Password updated....";

			}

			tx.commit(); // hibernate perform automatic dirty checking compare l1 to DB : update query l1
							// cached destroyed and db cn returns to the pool

		} catch (RuntimeException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}

		if (u != null) {
			u.setPassword("9999"); // here we are updating state of detached pojo : hibernate will not do it :
									// session is closed
		}

		return mesg;
	}

}
