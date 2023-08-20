package pojos;

import javax.persistence.*;

@Entity
@Table(name = "accounts_tbl")
public class BankAccount extends BaseEntity {

	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private double balance;
	@ManyToOne
	@JoinColumn(name = "vendor_id")
	private Vendor currentVendor;

	public BankAccount() {
		System.out.println("In Bank Account ctor");
	}

	public BankAccount(AccountType accountType, double balance) {
		super();
		this.accountType = accountType;
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Vendor getCurrentVendor() {
		return currentVendor;
	}

	public void setCurrentVendor(Vendor currentVendor) {
		this.currentVendor = currentVendor;
	}

	@Override
	public String toString() {
		return "BankAccount [accountType=" + accountType + ", balance=" + balance + "]";
	}

}
