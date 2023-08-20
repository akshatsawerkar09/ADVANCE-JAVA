package dao;

import java.sql.SQLException;

import pojos.Voter;

public interface IVoterDao {

	Voter validateUser(String email, String password) throws SQLException;

	String updateVotingStatus(int voterId) throws SQLException;

}
