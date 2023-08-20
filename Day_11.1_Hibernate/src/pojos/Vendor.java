package pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vendors")
public class Vendor extends BaseEntity {

	@Column(length = 20)
	private String name;
	@Column(length = 20, unique = true)
	private String email;
	@Column(length = 20, nullable = false)
	private String password;
	@Column(name = "reg_amt")
	private double regAmount;
	@Column(name = "reg_date")
	private LocalDate regDate;
	@OneToMany(mappedBy = "acctOwner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BankAccount> accounts = new ArrayList<>();
	// one to one uni-dir mapping between entity (vendor) -> value type(payment
	// details)
	@Embedded // Optional annotation
	private PaymentDetails details;
	// one to many uni-dir mapping between vendor(entity)1--->* String(Phone Number)
	// how to tell hibernate : whatever follows is collection of basic value types
	@ElementCollection // Mandatory o.w hibernate throws mapping exception
	// how to specify name of the collection table & FK col name
	// optional but recommended for clarity
	@CollectionTable(name = "vendor_phones", joinColumns = @JoinColumn(name = "vendor_id"))
	@Column(name = "phone_no", length = 10, unique = true)
	private List<String> phoneNos = new ArrayList<>();
	// one to many uni-directional association between entity(vendor) -> * Vendor
	// Service(value)
	// collection of composite embedable types
	@ElementCollection
	@CollectionTable(name = "vendor_services", joinColumns = @JoinColumn(name = "v_id"))
	private List<VendorService> services = new ArrayList<>();

	public Vendor() {
		System.out.println("In Vendor ctor");
	}

	public Vendor(String name, String email, String password, double regAmount, LocalDate regDate) {
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

	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<BankAccount> accounts) {
		this.accounts = accounts;
	}

	public PaymentDetails getDetails() {
		return details;
	}

	public void setDetails(PaymentDetails details) {
		this.details = details;
	}

	public List<String> getPhoneNos() {
		return phoneNos;
	}

	public void setPhoneNos(List<String> phoneNos) {
		this.phoneNos = phoneNos;
	}

	public List<VendorService> getServices() {
		return services;
	}

	public void setServices(List<VendorService> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "Vendor [name=" + name + ", email=" + email + ", password=" + password + ", regAmount=" + regAmount
				+ ", regDate=" + regDate + "]";
	}

	// helper method
	public void addAccount(BankAccount a) {
		accounts.add(a);
		a.setAcctOwner(this);
	}

	public void removeAccount(BankAccount a) {
		accounts.remove(a);
		a.setAcctOwner(null);
	}

}
