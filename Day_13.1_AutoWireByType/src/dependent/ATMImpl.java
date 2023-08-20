package dependent;

import dependency.TestTransport;
import dependency.Transport;

public class ATMImpl implements ATM {

	/*
	 * 1.1 myTransport = null private TestTransport myTransport;
	 */
	// 1.2 private TestTransport myTransport = new TestTransport(); // not null

	// 1.3
	private Transport myTransport;

	public ATMImpl() {
		System.out.println("in cnstr of " + getClass().getName() + " " + myTransport);
	}

	@Override
	public void deposit(double amt) {
		System.out.println("depositing " + amt);
		byte[] data = ("depositing " + amt).getBytes();
		myTransport.informBank(data);

	}

	@Override
	public void withdraw(double amt) {
		System.out.println("withdrawing " + amt);
		byte[] data = ("withdrawing " + amt).getBytes();
		myTransport.informBank(data);
	}

	// 1.4 setter based DI
	public void setMyTransport(Transport myTransport) {
		System.out.println("In set transport");
		this.myTransport = myTransport;
	}

	// init style method
	public void myInit() {
		System.out.println("In init " + myTransport);
	}

	// Destroy style method
	public void myDestroy() {
		System.out.println("In destroy " + myTransport);
	}

}
