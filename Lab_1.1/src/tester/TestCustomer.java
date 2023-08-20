package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.CustomerDaoImpl;
import pojos.Customer;

public class TestCustomer {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			CustomerDaoImpl customerDao = new CustomerDaoImpl();

			while (true) {

				System.out.println(
						"Menu\n1.Authenticate Customer\n2.User Registration\n3.Change Password\n4.Unsubscribe Customer");

				switch (sc.nextInt()) {
				case 1:
					System.out.println("Enter email and password");
					Customer customer = customerDao.authenticateCustomer(sc.next(), sc.next());
					System.out.println(customer);
					break;
				case 2:
					System.out.println("Enter depositAmount, email, name, password,regDate, role");
					System.out.println(customerDao.registerCustomer(sc.nextDouble(), sc.next(), sc.next(), sc.next(),
							Date.valueOf(sc.next()), sc.next()));
					break;
				case 3:
					System.out.println("Ente email , oldpassword, newPassword");
					System.out.println(customerDao.changePassword(sc.next(), sc.next(), sc.next()));
					break;
				case 4:
					System.out.println("Enter customerId");
					System.out.println(customerDao.unsubscriberCustomer(sc.nextInt()));
					break;
				default:
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
