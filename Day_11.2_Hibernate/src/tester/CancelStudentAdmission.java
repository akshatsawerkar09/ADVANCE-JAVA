package tester;

import static utils.HibernateUtils.getSf;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import dao.StudenDaoImpl;

public class CancelStudentAdmission {

	public static void main(String[] args) {

		try (SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)) {

			StudenDaoImpl studenDao = new StudenDaoImpl();

			System.out.println("Enter course name & student id");

			System.out.println(studenDao.cancelAdmission(sc.next(), sc.nextInt()));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
