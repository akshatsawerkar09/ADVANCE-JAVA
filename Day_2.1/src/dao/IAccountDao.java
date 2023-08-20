package dao;

import java.sql.SQLException;

public interface IAccountDao {

	// Add a method for funds transfer
	String transferFunds(int srcId, int destId, double amount) throws SQLException;

}
