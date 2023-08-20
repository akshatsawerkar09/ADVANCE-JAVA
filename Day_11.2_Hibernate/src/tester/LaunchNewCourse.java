package tester;

import static utils.HibernateUtils.getSf;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.CourseDaoImpl;
import pojos.Course;

public class LaunchNewCourse {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			// for conversion from string to java.util.Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			System.out.println("Enter course details title, startDate, endDate, fees, capacity");

			CourseDaoImpl courseDao = new CourseDaoImpl();

			Course c = new Course(sc.next(), sdf.parse(sc.next()), sdf.parse(sc.next()), sc.nextDouble(), sc.nextInt());

			System.out.println(courseDao.launchNewCourse(c));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
