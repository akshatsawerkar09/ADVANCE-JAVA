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

	private ATMImpl(double cash123, Transport t) {
		cash = cash123;
		myTransport = t;
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

	// factory method based D.I
	// this factory method should have to static because this method itself create
	// bean instance
	public static ATMImpl myFactory(double cash123, Transport t) {
		System.out.println("In Factory method");
		return new ATMImpl(cash123, t);
	}

}
