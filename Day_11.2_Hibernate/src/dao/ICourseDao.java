package dao;

import pojos.Course;

public interface ICourseDao {

	// 1
	String launchNewCourse(Course c); // c: transient

	// 3
	String cancelCourse(int courseId);

	// 5
	Course getCourseDetails(String courseName);

}
