package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import static utils.DBUtils.fetchDBConnection;

public class AccountDaoImpl implements IAccountDao {

	private Connection cn;
	private CallableStatement cst1;

	public AccountDaoImpl() throws ClassNotFoundException, SQLException {
		// get cn from DBUtils
		cn = fetchDBConnection();
		cst1 = cn.prepareCall("{call transfer_funds(?,?,?,?,?)}");
		// register out parameters
		cst1.registerOutParameter(4, Types.DOUBLE);
		cst1.registerOutParameter(5, Types.DOUBLE);
		System.out.println("Account dao created...");
	}

	@Override
	public String transferFunds(int srcId, int destId, double amount) throws SQLException {

		cst1.setInt(1, srcId);
		cst1.setInt(2, destId);
		cst1.setDouble(3, amount);

		// execute stored procedure
		cst1.execute();

		return "Updated src balance " + cst1.getDouble(4) + " dest balance " + cst1.getDouble(5);
	}

	public void cleanUp() throws SQLException {
		if (cst1 != null) {
			cst1.close();
		}
		if (cn != null) {
			cn.close();
		}
	}

}
