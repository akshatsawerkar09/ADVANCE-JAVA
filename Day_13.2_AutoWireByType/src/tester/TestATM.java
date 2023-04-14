package tester;

import dependency.HttpTransport;
import dependent.ATM;
import dependent.ATMImpl;

public class TestATM {

	public static void main(String[] args) {

		/* 1.5 ATM ref = new ATMImpl(); */

		// 1.6
		ATMImpl ref = new ATMImpl();

		// 1.7
		ref.setMyTransport(new HttpTransport());

		ref.deposit(1000);

	}

}
