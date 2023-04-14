package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDaoImpl;
import pojos.Location;

public class AssignVendorLocation {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			VendorDaoImpl vendorDao = new VendorDaoImpl();

			System.out.println("Enter vendorId & location details : city, state, country");

			System.out.println(
					vendorDao.assignVendorLocation(sc.nextInt(), new Location(sc.next(), sc.next(), sc.next())));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
