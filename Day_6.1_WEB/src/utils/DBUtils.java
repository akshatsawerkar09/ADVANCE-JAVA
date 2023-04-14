package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBUtils {

	// Add a static method for getting a fixed DB connection
	static Connection fetchDBConnection(String drvrClass, String url, String userName, String password)
			throws ClassNotFoundException, SQLException {

		// Load Type IV JDBC Driver
		Class.forName(drvrClass);

		return DriverManager.getConnection(url, userName, password);
	}

}
