package tester;

import java.util.Scanner;

import dao.AccountDaoImpl;

public class TestStoredProcedure {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			// create dao instance
			AccountDaoImpl dao = new AccountDaoImpl();

			System.out.println("Enter src acct no, dest acct no n amount to transfer");

			System.out.println(dao.transferFunds(sc.nextInt(), sc.nextInt(), sc.nextDouble()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
