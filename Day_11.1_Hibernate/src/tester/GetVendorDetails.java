package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDaoImpl;

public class GetVendorDetails {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			VendorDaoImpl vendorDao = new VendorDaoImpl();

			System.out.println("Enter vendor email");

			System.out.println(vendorDao.fetchVendorDetails(sc.next()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
