package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.BankAccountDaoImpl;
import pojos.AccountType;
import pojos.BankAccount;

public class OpenAccount {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			BankAccountDaoImpl bankAccountDao = new BankAccountDaoImpl();

			System.out.println("Enter accountType, balance and vendorId");

			System.out.println(bankAccountDao.openBankAccount(
					new BankAccount(AccountType.valueOf(sc.next().toUpperCase()), sc.nextDouble()), sc.nextInt()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
