package tester;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.Role;
import pojos.User;

public class UserRegistration {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();

			System.out.println(
					"Enter User Details : name, email, password, userRole, confirmPassword, regAmount, regDate");

			User u = new User(sc.next(), sc.next(), sc.next(), Role.valueOf(sc.next().toUpperCase()), sc.next(),
					sc.nextDouble(), LocalDate.parse(sc.next()));

			// invoke dao's method for user registration
			System.out.println("Reg status " + dao.registerUser(u));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
