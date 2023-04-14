package pojos;

import java.time.LocalDate;
import javax.persistence.*;

/*userId (PK) :Integer ,name,email,password,role(enum),confirmPassword, regAmount;
LocalDate/Date regDate;
byte[] image;*/

// Mandatory rule for Hibernate POJO : POJO's ID property must be serializable
@Entity // mandatory
@Table(name = "users_tbl")
public class User {

	// here int will also work but int is not serializable : int to Integer auto
	// boxing
	private Integer userId;
	private String name, email, password;
	private Role userRole;
	private String confirmPassword;
	private double regAmount;
	private LocalDate regDate;
	private byte[] image;

	// default ctor : mandatory
	public User() {
		System.out.println("In User Ctor");
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

	// must supply all setter and getters
	@Id // mandatory : PK constraint : must be assigned by the user
	// if only @GeneratedValue // auto id generation by hibernate : native DB scheme
	// : AUTO
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto id generation by hibernate : auto increment best suited
														// for MySql
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(length = 20) // varchar : 20
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 20, unique = true) // unique constraint
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 15, nullable = false) // not null
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// if we don't supply than hibernate will pick up ordinal position 0,1,2... and
	// col type will be int
	@Enumerated(EnumType.STRING) // now coll will be varchar
	@Column(length = 30, name = "user_role")
	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	@Transient // skip from serialization means no column in DB
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

	@Lob // large objects -> col type : longblob
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
