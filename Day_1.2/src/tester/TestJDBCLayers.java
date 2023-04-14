package tester;

import java.sql.Date;
import java.util.Scanner;

import dao.EmployeeDaoImpl;

public class TestJDBCLayers {

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {

			EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

			System.out.println("Enter deptid, start & end date, enter 'quit' to exit");

			while (true) {

				String deptId = sc.next();

				if (deptId.equalsIgnoreCase("quit")) {
					break;
				}

				employeeDao.getAllEmpDetails(deptId, Date.valueOf(sc.next()), Date.valueOf(sc.next()))
						.forEach(System.out::println);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
