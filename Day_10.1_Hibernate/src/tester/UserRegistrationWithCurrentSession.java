package tester;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.Role;
import pojos.User;

public class UserRegistrationWithCurrentSession {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();

			System.out.println(
					"Enter User Details : name, email, password, userRole, confirmPassword, regAmount, regDate");

			User u = new User(sc.next(), sc.next(), sc.next(), Role.valueOf(sc.next().toUpperCase()), sc.next(),
					sc.nextDouble(), LocalDate.parse(sc.next()));

			// u.setUserId(2); Persistent object exception : detached entity passed to
			// persist

			// u.setUserId(2345); Persistent object exception : detached entity passed to

			// u.setUserId(1234);

			// if only this System.out.println("user id " + u.getUserId()); // null

			System.out.println("user id " + u.getUserId()); // not null : 1

			// invoke dao's method for user registration
			System.out.println("Reg status " + dao.registerUserWithGetCurrentSession(u));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
