package dependent;

import dependency.CustomerNotificationService;
import dependency.TestTransport;
import dependency.Transport;

public class ATMImpl implements ATM {

	/*
	 * 1.1 myTransport = null private TestTransport myTransport;
	 */
	// 1.2 private TestTransport myTransport = new TestTransport(); // not null

	// 1.3
	private Transport myTransport; // here we are injecting only http layer
	private CustomerNotificationService[] notification; // and here we are injectiing email and sms also that's why we
														// have to change to array

	public ATMImpl() {
		System.out.println("in cnstr of " + getClass().getName() + " " + myTransport);
	}

	@Override
	public void deposit(double amt) {
		System.out.println("depositing " + amt);
		byte[] data = ("depositing " + amt).getBytes();
		myTransport.informBank(data);
		// inform customer using multiple services
		for (CustomerNotificationService cns : notification)
			cns.informCustomer("You have desposited " + amt + " in your account");

	}

	@Override
	public void withdraw(double amt) {
		System.out.println("withdrawing " + amt);
		byte[] data = ("withdrawing " + amt).getBytes();
		myTransport.informBank(data);
		// inform customer using multiple services
		for (CustomerNotificationService cns : notification)
			cns.informCustomer("You have withdrawn " + amt + " in your account");
	}

	// 1.4 setter based DI
	public void setMyTransport(Transport myTransport) {
		System.out.println("In set transport");
		this.myTransport = myTransport;
	}

	public void setNotification(CustomerNotificationService[] notification) {
		System.out.println("In set notification");
		this.notification = notification;
	}

	// init style method
	public void myInit() {
		System.out.println("In init " + myTransport + " " + notification);
	}

	// Destroy style method
	public void myDestroy() {
		System.out.println("In destroy " + myTransport + " " + notification);
	}

}
