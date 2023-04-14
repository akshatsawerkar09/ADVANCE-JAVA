package dao;

import java.sql.Connection;
import static utils.DBUtils.fetchDBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import pojos.Candidate;

public class CandidateDaoImpl implements ICandidateDao {

	private Connection cn;
	private Statement st1;
	private PreparedStatement pst1, pst2, pst3;

	public CandidateDaoImpl() throws ClassNotFoundException, SQLException {
		String sql2 = "update candidates set votes = votes + 1 where id = ?";
		String sql3 = "select * from candidates limit 2";
		String sql4 = "select party, votes from candidates order by votes desc";
		cn = fetchDBConnection();
		st1 = cn.createStatement();
		pst1 = cn.prepareStatement(sql2);
		pst2 = cn.prepareStatement(sql3);
		pst3 = cn.prepareStatement(sql4);
		System.out.println("Candidate dao created....");

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

	@Override
	public List<Candidate> top2Analysis() throws SQLException {

		List<Candidate> candidates = new ArrayList<>();

		try (ResultSet rst = pst2.executeQuery()) {

			while (rst.next()) {
				candidates.add(new Candidate(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}

		}

		return candidates;
	}

	@Override
	public LinkedHashMap<String, Integer> partyWiseAnalysis() throws SQLException {

		LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();

		try (ResultSet rst = pst3.executeQuery()) {

			while (rst.next()) {
				map.put(rst.getString(1), rst.getInt(2));
			}

		}

		return map;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null || pst2 != null || pst3 != null || st1 != null) {
			pst1.close();
			pst2.close();
			pst3.close();
			st1.close();
		}
		if (cn != null) {
			cn.close();
		}
	}

}
