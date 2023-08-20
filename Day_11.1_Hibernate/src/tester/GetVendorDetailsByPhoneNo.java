package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDaoImpl;

public class GetVendorDetailsByPhoneNo {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			VendorDaoImpl vendorDao = new VendorDaoImpl();

			System.out.println("Enter vendor phoneNo");

			System.out.println(vendorDao.getVendorDetailsByPhoneNo(sc.next()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
