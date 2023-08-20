package dependency;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// prototype
// to tell SC this is spring bean if we don't supply id it will pick testTransport
@Component("test") // bean id = "test", class="dependency.TestTransport" scope="protoype"
@Scope(value = "prototype")
public class TestTransport implements Transport {

	public TestTransport() {
		System.out.println("in cnstr of " + getClass().getName());
	}

	@Override
	public void informBank(byte[] data) {
		System.out.println("informing bank using " + getClass().getName() + " layer");

	}

}
