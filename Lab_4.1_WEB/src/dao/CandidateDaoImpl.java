package dao;

import java.sql.Connection;
import static utils.DBUtils.fetchDBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojos.Candidate;

public class CandidateDaoImpl implements ICandidateDao {

	private Connection cn;
	private Statement st1;
	private PreparedStatement pst1;

	public CandidateDaoImpl() throws ClassNotFoundException, SQLException {
		String sql2 = "update candidates set votes = votes + 1 where id = ?";
		cn = fetchDBConnection();
		st1 = cn.createStatement();
		pst1 = cn.prepareStatement(sql2);

	}

	@Override
	public List<Candidate> listCandidates() throws SQLException {

		String sql = "select * from candidates";

		List<Candidate> candidates = new ArrayList<Candidate>();

		try (ResultSet rst = st1.executeQuery(sql)) {

			while (rst.next()) {
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}

		}

		return candidates;
	}

	@Override
	public String incrementVotes(int candidateId) throws SQLException {

		String message = "Unable to increment votes";

		pst1.setInt(1, candidateId);

		int check = pst1.executeUpdate();

		if (check > 0) {
			message = "Votes Incremented!!!";
		}

		return message;
	}

}
