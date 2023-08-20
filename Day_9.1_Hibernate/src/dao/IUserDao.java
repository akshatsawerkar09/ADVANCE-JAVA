package dao;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import pojos.Role;
import pojos.User;

public interface IUserDao {

	// Add a method for user registration : open session
	String registerUser(User user);

	// Add a method for user registration : get current session
	String registerUserWithGetCurrentSession(User user);

	// Add a method to fetch user details by user id
	User getUserDetails(int userId);

	// Add a method to fetch all user details
	List<User> fetchAllUserDetails();

	// Add a method for fetching selected user details
	List<User> fetchSelectedUserDetails(LocalDate start, LocalDate end, Role useRole);

	// Add a method for user validation
	User authenticateUser(String email, String pwd);

	// Add a method for fetching selected user names with some criteria
	List<String> fetchSelectedUserNames(LocalDate start, LocalDate end, Role useRole);

	// Add a method for fetching selected user names, regAmt, regDate with some
	// criteria
	List<User> fetchSelectedUserDetailsConstrExpr(LocalDate start, LocalDate end, Role useRole);

	// Add a method to change password
	String changePassword(int userId, String newPwd);

	// Add a method to apply discount who have reged before a particular reg date
	String applyDiscount(double discount, LocalDate date);

	// Add a method to unsubscribe user
	String deleteUserDetails(int userId);

	// Add a method to save an image in db
	String saveImage(String email, String imageFilePath) throws IOException;

	// Add a method restore image from db
	String restoreImage(int userId, String destImageFilePath) throws IOException;
}
