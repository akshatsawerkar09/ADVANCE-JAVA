package tester;

import static utils.HibernateUtils.getSf;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDaoImpl;
import pojos.Vendor;

public class RegisterVendor {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			VendorDaoImpl vendorDao = new VendorDaoImpl();

			System.out.println("Enter name, email, password, regAmount, regDate");

			System.out.println(vendorDao.registerVendor(
					new Vendor(sc.next(), sc.next(), sc.next(), sc.nextDouble(), sdf.parse(sc.next()))));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
