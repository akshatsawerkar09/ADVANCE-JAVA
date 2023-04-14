package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.UserDaoImpl;

public class RestoreImageFromDB {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			// create dao instance
			UserDaoImpl dao = new UserDaoImpl();

			System.out.println("Enter used id & image file path to save image restored from DB");

			System.out.println(dao.restoreImage(sc.nextInt(), sc.next()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
