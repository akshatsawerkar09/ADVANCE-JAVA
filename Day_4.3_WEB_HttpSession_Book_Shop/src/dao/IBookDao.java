package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Book;

public interface IBookDao {

	// Add a method for getting all categories from the books table
	// here return type is not books because we only want categories and category
	// data type is string
	List<String> getAllCategories() throws SQLException;

	// Add a method for getting all available books under specific category
	List<Book> getBooksByCategory(String categoryName) throws SQLException;

}
