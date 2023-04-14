package pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "students_tbl")
public class Student extends BaseEntity {

	@Column(length = 20)
	private String name;
	@Column(length = 20, unique = true)
	private String email;
	// many to one bi-dir association between 2 entity (Student & course)
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false) // to change the derived name
	private Course selectedCourse;
	// one to one uni-dir mapping between entity (Student)-->value(Adhar card)
	// limitations of value type 1. no seperate existence
	// 2. can't be accessed directly from jpql can be accessed via entity ref
	// 3. uni dir
	@Embedded // optional
	private AdharCard card;
	// one to many , un-dir association from entity --> student to collection of
	// composite value type edu
	@ElementCollection
	@CollectionTable(name = "student_qualifications", joinColumns = @JoinColumn(name = "student_id"))
	private List<EducationalQualifications> qualifications = new ArrayList<>();

	public Student() {
		System.out.println("In student ctor");
	}

	public Student(String name, String email) {
		super();
		this.name = name;
		this.email = email;
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

	public Course getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(Course selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

	public AdharCard getCard() {
		return card;
	}

	public void setCard(AdharCard card) {
		this.card = card;
	}

	public List<EducationalQualifications> getQualifications() {
		return qualifications;
	}

	public void setQualifications(List<EducationalQualifications> qualifications) {
		this.qualifications = qualifications;
	}

	@Override
	public String toString() {
		return "Student  [id=" + getId() + ", name=" + name + ", email=" + email + "]";
	}

}
