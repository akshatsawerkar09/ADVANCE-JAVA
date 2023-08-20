package dao;

import static utils.DBUtils.fetchDBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Employee;

public class EmployeeDaoImpl implements IEmployeeDao {

	private Connection cn;
	private PreparedStatement pst1;

	public EmployeeDaoImpl() throws ClassNotFoundException, SQLException {
		String sql1 = "select empid, name, salary, join_date from my_emp where deptid = ? and join_date between ? and ?";
		cn = fetchDBConnection();
		pst1 = cn.prepareStatement(sql1);
		System.out.println("Emp dao created....");
	}

	@Override
	public List<Employee> getAllEmpDetails(String dept, Date startDate, Date endDate) throws SQLException {

		List<Employee> emps = new ArrayList<>();

		pst1.setString(1, dept);
		pst1.setDate(2, startDate);
		pst1.setDate(3, endDate);

		try (ResultSet rst = pst1.executeQuery()) {

			while (rst.next()) {
				emps.add(new Employee(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getDate(4)));
			}
		}

		return emps;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}
		if (cn != null) {
			cn.close();
		}
	}

}
