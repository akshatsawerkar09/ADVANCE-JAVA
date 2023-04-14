package dao;

import pojos.Student;

public interface IStudentDao {

	// 2 student admission
	String admitStudent(Student student, String courseTitle);

	// 4
	String cancelAdmission(String courseName, int studentId);
	
	

}
