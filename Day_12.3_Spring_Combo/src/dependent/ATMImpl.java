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
	private double cash;

	// constr based DI
	public ATMImpl(double cash123) {
		cash = cash123;
		System.out.println("in cnstr of " + getClass().getName() + " " + myTransport + " " + cash);
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

	// init style method
	public void myInit() {
		System.out.println("In init " + myTransport + " " + cash);
	}

	// Destroy style method
	public void myDestroy() {
		System.out.println("In destroy " + myTransport + " " + cash);
	}

	// setter based DI
	public void setMyTransport(Transport myTransport) {
		System.out.println("In set transport");
		this.myTransport = myTransport;
	}

}
