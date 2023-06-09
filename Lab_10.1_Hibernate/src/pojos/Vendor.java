package pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "vendors_tbl")
public class Vendor extends BaseEntity {

	@Column(length = 25)
	private String name;
	@Column(length = 25, unique = true)
	private String email;
	@Column(length = 25)
	private String password;
	@Column(name = "reg_amount")
	private double regAmount;
	@Column(name = "reg_date")
	@Temporal(TemporalType.DATE)
	private Date regDate;
	@OneToMany(mappedBy = "currentVendor", cascade = CascadeType.ALL)
	private List<BankAccount> accounts = new ArrayList<>();

	public Vendor() {
		System.out.println("In Vendor ctor");
	}

	public Vendor(String name, String email, String password, double regAmount, Date regDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Vendor [name=" + name + ", email=" + email + ", password=" + password + ", regAmount=" + regAmount
				+ ", regDate=" + regDate + "]";
	}

	public void addBankAccount(BankAccount account) {
		accounts.add(account);
		account.setCurrentVendor(this);
	}

	public void removeBankAccount(BankAccount account) {
		accounts.remove(account);
		account.setCurrentVendor(null);
	}

}
