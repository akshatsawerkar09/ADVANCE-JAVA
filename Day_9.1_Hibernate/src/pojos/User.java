package pojos;

import java.time.LocalDate;
import javax.persistence.*;

/*userId (PK) :Integer ,name,email,password,role(enum),confirmPassword, regAmount;
LocalDate/Date regDate;
byte[] image;*/

// Mandatory
@Entity
@Table(name = "users_tbl")
public class User {

	// Mandatory : must define unique id property : Must be java.io.serializable i/f
	// int will also work int -> Integer auto boxing
	private Integer userId;
	private String name, email, password;
	private Role userRole;
	private String confirmPassword;
	private double regAmount;
	private LocalDate regDate;
	private byte[] image;

	// Mandatory : must supply argument less default constructor
	public User() {
		System.out.println("In User ctor");
	}

	public User(String name, String email, String password, Role userRole, String confirmPassword, double regAmount,
			LocalDate regDate) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
		this.confirmPassword = confirmPassword;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	public User(String name, double regAmount, LocalDate regDate) {
		super();
		this.name = name;
		this.regAmount = regAmount;
		this.regDate = regDate;
	}

	// must supply all setters & getters
	// Mandatory : PK constraint
	@Id
	// @GeneratedValue strategy for id generation : auto -> as per native DB
	@GeneratedValue(strategy = GenerationType.IDENTITY) // most suitable for MySql auto-increment
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(length = 25)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 20, unique = true, nullable = false) // unique & not null constraint
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 20, nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// if we don't add annotation it will pick ordinal position : i.e int
	@Enumerated(EnumType.STRING)
	@Column(length = 20, name = "user_role")
	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	// skip from serializable : no column in db
	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Column(name = "reg_amount")
	public double getRegAmount() {
		return regAmount;
	}

	public void setRegAmount(double regAmount) {
		this.regAmount = regAmount;
	}

	@Column(name = "reg_date")
	public LocalDate getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	@Lob
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", userRole=" + userRole + ", confirmPassword=" + confirmPassword + ", regAmount=" + regAmount
				+ ", regDate=" + regDate + "]";
	}

}
