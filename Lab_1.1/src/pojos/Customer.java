package pojos;

import java.sql.Date;

// id | deposit_amt | email | name | password | reg_date | role

public class Customer {

	private int id;
	private double depositAmount;
	private String email;
	private String name;
	private String password;
	private Date regDate;
	private String role;

	public Customer() {

	}

	public Customer(int id, double depositAmount, String email, String name, String password, Date regDate,
			String role) {
		super();
		this.id = id;
		this.depositAmount = depositAmount;
		this.email = email;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", depositAmount=" + depositAmount + ", email=" + email + ", name=" + name
				+ ", regDate=" + regDate + ", role=" + role + "]";
	}

}
