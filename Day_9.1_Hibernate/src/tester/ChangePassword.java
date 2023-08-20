package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;

public class ChangePassword {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();

			System.out.println("Enter used id and new password");

			System.out.println(dao.changePassword(sc.nextInt(), sc.next()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
