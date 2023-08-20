package dependency;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// singleton and eager id = httpTransport
@Component
@Scope(value = "prototype")
public class HttpTransport implements Transport {

	public HttpTransport() {
		System.out.println("in cnstr of " + getClass().getName());
	}

	@Override
	public void informBank(byte[] data) {
		System.out.println("informing bank using " + getClass().getName() + " layer");

	}

}
