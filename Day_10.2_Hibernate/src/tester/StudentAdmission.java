package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudenDaoImpl;
import pojos.Student;

public class StudentAdmission {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			System.out.println("Enter student name, email & course tile");

			StudenDaoImpl studenDao = new StudenDaoImpl();

			System.out.println(studenDao.admitStudent(new Student(sc.next(), sc.next()), sc.next()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
