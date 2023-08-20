package dao;

import java.sql.Date;
import java.sql.SQLException;

import pojos.Customer;

public interface ICustomerDao {

	Customer authenticateUser(String email, String pwd) throws SQLException;

	String registerCustomer(double depositAmount, String email, String name, String password, Date regDate, String role)
			throws SQLException;

	String changePassword(String email, String oldPassword, String newPassword) throws SQLException;

	String unsubscriberCustomer(int customerId) throws SQLException;

}
