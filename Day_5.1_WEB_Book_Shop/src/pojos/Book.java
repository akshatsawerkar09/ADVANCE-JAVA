package pojos;

// id | title | author | category | price

public class Book {

	private int bookId;
	private String title, author, category;
	private double price;

	public Book() {

	}

	public Book(int bookId, String title, String author, String category, double price) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", price=" + price + "]";
	}

	/*
	 * @Override public String toString() { return "Book [bookId=" + bookId +
	 * ", title=" + title + ", author=" + author + ", category=" + category +
	 * ", price=" + price + "]"; }
	 */

}
