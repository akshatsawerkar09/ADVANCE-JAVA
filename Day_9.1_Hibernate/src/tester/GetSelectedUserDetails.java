package tester;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;
import pojos.Role;

public class GetSelectedUserDetails {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();

			System.out.println("Enter start date, end date & role");

			dao.fetchSelectedUserDetails(LocalDate.parse(sc.next()), LocalDate.parse(sc.next()),
					Role.valueOf(sc.next().toUpperCase())).forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
