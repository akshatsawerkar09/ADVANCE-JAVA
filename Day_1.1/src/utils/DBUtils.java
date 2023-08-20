package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBUtils {

	// Add a static method for getting a fixed DB connection
	static Connection fetchDBConnection() throws ClassNotFoundException, SQLException {

		// Load Type IV MySql supplied JDBC Driver class under metaspace
		Class.forName("com.mysql.cj.jdbc.Driver");// this step is optional

		// get the fixed connection to DB
		String url = "jdbc:mysql://localhost:3306/acts?useSSL=false";
		// after false add &allowPublicKeyRetrieval=true

		return DriverManager.getConnection(url, "root", "Akshat@123");
	}

}
