package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Candidate;

public interface ICandidateDao {

	List<Candidate> listCandidates() throws SQLException;

	String incrementVotes(int candidateId) throws SQLException;

}
