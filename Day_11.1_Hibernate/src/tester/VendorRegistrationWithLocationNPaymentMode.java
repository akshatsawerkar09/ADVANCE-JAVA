package tester;

import static utils.HibernateUtils.getSf;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.VendorDaoImpl;
import pojos.AcctType;
import pojos.BankAccount;
import pojos.Location;
import pojos.PaymentDetails;
import pojos.PaymentMode;
import pojos.Vendor;

public class VendorRegistrationWithLocationNPaymentMode {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			VendorDaoImpl vendorDao = new VendorDaoImpl();

			System.out.println("Enter vendor details : name, email, password, regAmount, regDate");

			Vendor v = new Vendor(sc.next(), sc.next(), sc.next(), sc.nextDouble(), LocalDate.parse(sc.next()));

			System.out.println("Enter 1st Account details : account type & balance");

			BankAccount a1 = new BankAccount(AcctType.valueOf(sc.next().toUpperCase()), sc.nextDouble());

			v.addAccount(a1);

			System.out.println("Enter 2nd Account details : account type & balance");

			BankAccount a2 = new BankAccount(AcctType.valueOf(sc.next().toUpperCase()), sc.nextDouble());

			v.addAccount(a2);

			// accept location details
			System.out.println("Enter location details: city, state, country");

			Location location = new Location(sc.next(), sc.next(), sc.next());

			// here location data won't be added in DB because location and vendor one to
			// one uni-directional and we have to explicitly persist location record so we
			// have registerVendor method and vendor does'nt have location so we have to
			// create another method in dao layer
			location.setCurrentVendor(v);

			// accept payment details
			System.out.println("Enter payment details : mode, paymentDate, amount");

			v.setDetails(new PaymentDetails(PaymentMode.valueOf(sc.next().toUpperCase()), LocalDate.parse(sc.next()),
					sc.nextDouble()));

			System.out.println(vendorDao.registerVendor(v));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
