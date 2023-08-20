package tester;

import java.sql.Connection;
import static utils.DBUtils.fetchDBConnection;

public class TestConnection {

	public static void main(String[] args) {

		// connection i/f is auto-closeable

		try (Connection cn = fetchDBConnection()) {

			System.out.println("Connected to DB : " + cn);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
