package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.CourseDaoImpl;
import pojos.Course;

public class GetCourseDetails {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			System.out.println("Enter course tile");

			System.out.println("Course Details");

			CourseDaoImpl courseDao = new CourseDaoImpl();

			Course c = courseDao.getCourseDetails(sc.next());

			System.out.println(c);

			System.out.println("Student Details associated with the course ");

			c.getStudents().forEach(System.out::println); // here we are getting lazy initialization exception

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
