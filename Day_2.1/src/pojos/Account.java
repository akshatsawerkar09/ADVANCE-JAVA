package pojos;

public class Account {

	private int acctNo;
	private String customerName, acType;
	private double balance;

	public Account() {

	}

	public Account(int acctNo, String customerName, String acType, double balance) {
		super();
		this.acctNo = acctNo;
		this.customerName = customerName;
		this.acType = acType;
		this.balance = balance;
	}

	public int getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(int acctNo) {
		this.acctNo = acctNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAcType() {
		return acType;
	}

	public void setAcType(String acType) {
		this.acType = acType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [acctNo=" + acctNo + ", customerName=" + customerName + ", acType=" + acType + ", balance="
				+ balance + "]";
	}

}
