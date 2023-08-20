package pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "courses_tbl")
public class Course extends BaseEntity {

	@Column(length = 30)
	private String title;
	// local date means only date but here we are using Date means date and time
	// both so to avoid db col to be date and time we are using this annotations
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	private double fees;
	private int capacity;
	// one to many association :initialize because at the launching course it will
	// null
	// if we don't add anno it will give mapping exception
	// value of mappedBy must be name of the property as it appears in the owning
	// side
	@OneToMany(mappedBy = "selectedCourse", cascade = CascadeType.ALL, orphanRemoval = true/*
																							 * , fetch = FetchType.EAGER
																							 */)
	private List<Student> students = new ArrayList<>();

	public Course() {
		System.out.println("In course ctor");
	}

	public Course(String title, Date startDate, Date endDate, double fees, int capacity) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fees = fees;
		this.capacity = capacity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	// tip : avoid association based property because student will student toString
	// and in Student class Course will call course toString
	// which will cause infinite loop
	@Override
	public String toString() {
		return "Course [title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", fees=" + fees
				+ ", capacity=" + capacity + "]";
	}

	// add helper methods for bi-dir : not mandatory : recommended
	public void addStudent(Student student) {
		students.add(student); // parent to child
		student.setSelectedCourse(this);// child to parent

	}

	public void removeStudent(Student student) {
		students.remove(student); // here in cancel admission hibernate is doing null value but not deleting the
									// orphan records
		student.setSelectedCourse(null);
	}

}
