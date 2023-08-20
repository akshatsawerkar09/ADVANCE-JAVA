package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utils.DBUtils.fetchDBConnection;

import pojos.Voter;

public class VoterDaoImpl implements IVoterDao {

	private Connection cn;
	private PreparedStatement pst1, pst2;

	public VoterDaoImpl() throws ClassNotFoundException, SQLException {
		String sql1 = "select * from voters where email = ? and password = ?";
		String sql2 = "update voters set status = 1 where id = ?";
		cn = fetchDBConnection();
		pst1 = cn.prepareStatement(sql1);
		pst2 = cn.prepareStatement(sql2);
		System.out.println("Voter Dao Created....");
	}

	@Override
	public Voter validateUser(String email, String password) throws SQLException {

		Voter voter = null;

		pst1.setString(1, email);
		pst1.setString(2, password);

		try (ResultSet rst = pst1.executeQuery()) {

			if (rst.next()) {
				voter = new Voter(rst.getInt(1), rst.getString(2), email, password, rst.getBoolean(5),
						rst.getString(6));
			}

		}
		return voter;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null) {
			pst1.close();
		}
		if (cn != null) {
			cn.close();
		}
	}

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {

		String message = "Voting status updation failed!!!";

		pst2.setInt(1, voterId);

		if (pst2.executeUpdate() > 0) {
			message = "Voting status updated!!!!";
		}

		return message;
	}

}
