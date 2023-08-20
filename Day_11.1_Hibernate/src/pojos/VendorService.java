package pojos;

import javax.persistence.*;

@Embeddable
public class VendorService {

	@Column(name = "service_name", length = 20)
	private String serviceName;
	@Column(name = "contract_period")
	private int contractPeriod;
	private double charges;

	public VendorService() {
		System.out.println("In Vendor Service ctor");
	}

	public VendorService(String serviceName, int contractPeriod, double charges) {
		super();
		this.serviceName = serviceName;
		this.contractPeriod = contractPeriod;
		this.charges = charges;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public int getContractPeriod() {
		return contractPeriod;
	}

	public void setContractPeriod(int contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public double getCharges() {
		return charges;
	}

	public void setCharges(double charges) {
		this.charges = charges;
	}

	@Override
	public String toString() {
		return "VendorService [serviceName=" + serviceName + ", contractPeriod=" + contractPeriod + ", charges="
				+ charges + "]";
	}

}
