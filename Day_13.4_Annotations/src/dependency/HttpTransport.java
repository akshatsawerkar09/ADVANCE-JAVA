package dependency;

import org.springframework.stereotype.Component;

// singleton and eager id = http
@Component("http")
public class HttpTransport implements Transport {

	public HttpTransport() {
		System.out.println("in cnstr of " + getClass().getName());
	}

	@Override
	public void informBank(byte[] data) {
		System.out.println("informing bank using " + getClass().getName() + " layer");

	}

}
