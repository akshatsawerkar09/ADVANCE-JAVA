package tester;

import static utils.DBUtils.fetchDBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class TestPST {

	public static void main(String[] args) {

		String sql = "select empid, name, salary, join_date from my_emp where deptid =? and join_date between ? and ?";

		try (Connection cn = fetchDBConnection();
				PreparedStatement pst = cn.prepareStatement(sql);
				Scanner sc = new Scanner(System.in)) {

			System.out.println("Enter deptid, start & end date, enter 'quit' to exit");

			while (true) {

				String deptId = sc.next();

				if (deptId.equalsIgnoreCase("quit")) {
					break;
				}

				// set IN params
				pst.setString(1, deptId);
				pst.setDate(2, Date.valueOf(sc.next()));
				pst.setDate(3, Date.valueOf(sc.next()));

				// execute query
				try (ResultSet rst = pst.executeQuery()) {
					// while for multiple record & if for single record
					while (rst.next()) {
						System.out.printf("ID %d Name %s Salary %.1f Joined on %s%n", rst.getInt(1), rst.getString(2),
								rst.getDouble(3), rst.getDate(4));
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
