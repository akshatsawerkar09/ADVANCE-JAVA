package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.Customer;
import static utils.DBUtils.fetchDBConnection;

public class CustomerDaoImpl implements ICustomerDao {

	private Connection cn;
	private PreparedStatement pst1, pst2, pst3, pst4;

	public CustomerDaoImpl() throws ClassNotFoundException, SQLException {
		String sql1 = "select * from my_customers where email =? and password =?";
		String sql2 = "insert into my_customers (deposit_amt, email, name, password, reg_date, role) values (?,?,?,?,?,?)";
		String sql3 = "update my_customers set password = ? where email =? and password =?";
		String sql4 = "delete from my_customers where id =?";
		cn = fetchDBConnection();
		pst1 = cn.prepareStatement(sql1);
		pst2 = cn.prepareStatement(sql2);
		pst3 = cn.prepareStatement(sql3);
		pst4 = cn.prepareStatement(sql4);
	}

	@Override
	public Customer authenticateCustomer(String email, String password) throws SQLException {

		Customer customer = null;

		pst1.setString(1, email);
		pst1.setString(2, password);

		try (ResultSet rst = pst1.executeQuery()) {
			if (rst.next()) {
				customer = new Customer(rst.getInt(1), rst.getDouble(2), email, rst.getString(4), password,
						rst.getDate(6), rst.getString(7));
			}
		}

		return customer;
	}

	@Override
	public String registerCustomer(double depositAmount, String email, String name, String password, Date regDate,
			String role) throws SQLException {

		String message = "Customer Registration Failed!!!";

		pst2.setDouble(1, depositAmount);
		pst2.setString(2, email);
		pst2.setString(3, name);
		pst2.setString(4, password);
		pst2.setDate(5, regDate);
		pst2.setString(6, role);

		int check = pst2.executeUpdate();

		if (check > 0) {
			message = "Customer Registered Successfully!!!!";
		}

		return message;
	}

	@Override
	public String changePassword(String email, String oldPassword, String newPassword) throws SQLException {

		String message = "Change Password Failed!!!!";

		pst3.setString(1, newPassword);
		pst3.setString(2, email);
		pst3.setString(3, oldPassword);

		int check = pst3.executeUpdate();

		if (check > 0) {
			message = "Password Changed Successfully!!!!";
		}

		return message;
	}

	@Override
	public String unsubscriberCustomer(int customerId) throws SQLException {

		String message = "Customer Unsubscription Failed!!!!!";

		pst4.setInt(1, customerId);

		int check = pst4.executeUpdate();

		if (check > 0) {
			message = "Customer Unsubscribed!!!!!";
		}
		return message;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null || pst2 != null || pst3 != null || pst4 != null) {
			pst4.close();
			pst3.close();
			pst2.close();
			pst1.close();
		}
		if (cn != null) {
			cn.close();
		}
	}

}
