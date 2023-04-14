package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static utils.DBUtils.fetchDBConnection;

import java.util.ArrayList;
import java.util.List;

import pojos.Book;

public class BookDaoImpl implements IBookDao {

	private Connection cn;
	private PreparedStatement pst1, pst2, pst3;

	public BookDaoImpl() throws ClassNotFoundException, SQLException {
		cn = fetchDBConnection();
		pst1 = cn.prepareStatement("select distinct category from dac_books");
		pst2 = cn.prepareStatement("select * from dac_books where category = ?");
		pst3 = cn.prepareStatement("select * from dac_books where id = ?");
		System.out.println("Book dao created....");
	}

	@Override
	public List<String> getAllCategories() throws SQLException {

		List<String> categoryNames = new ArrayList<>();

		try (ResultSet rst = pst1.executeQuery()) {
			while (rst.next()) {
				categoryNames.add(rst.getString(1));
			}
		}

		return categoryNames;
	}

	@Override
	public List<Book> getBooksByCategory(String categoryName) throws SQLException {

		List<Book> books = new ArrayList<Book>();

		pst2.setString(1, categoryName);

		try (ResultSet rst = pst2.executeQuery()) {

			while (rst.next()) {

				books.add(new Book(rst.getInt(1), rst.getString(2), rst.getString(3), categoryName, rst.getDouble(5)));

			}

		}

		return books;
	}

	@Override
	public Book getBookDetailsById(int bookId) throws SQLException {

		pst3.setInt(1, bookId);

		try (ResultSet rst = pst3.executeQuery()) {

			if (rst.next()) {
				return new Book(bookId, rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5));
			}

		}

		return null;
	}

	public void cleanUp() throws SQLException {
		if (pst1 != null || pst2 != null || pst3 != null) {
			pst1.close();
			pst2.close();
			pst3.close();
		}
		if (cn != null) {
			cn.close();
		}
	}

}
