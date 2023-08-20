package dependent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import dependency.Transport;

// singleton and eager, dependency : http layer
@Component("my_atm")
@Scope(value = "prototype")
public class ATMImpl implements ATM {

	/*
	 * 1.1 myTransport = null private TestTransport myTransport;
	 */
	// 1.2 private TestTransport myTransport = new TestTransport(); // not null

	// 1.3
	// field level dependency injection
	// when we use only @Autowired it is by type (w/o explicit setter)
	@Autowired(required = false)
	@Qualifier("httpTransport123") // to replace by type to by name
	// meaning SC will try to search for the bean id = "httpTransport"
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

	// init style method
	@PostConstruct
	public void myInit() {
		System.out.println("In init " + myTransport);
	}

	// Destroy style method
	@PreDestroy
	public void myDestroy() {
		System.out.println("In destroy " + myTransport);
	}

}
