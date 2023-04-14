package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDaoImpl;
import pojos.Book;
import pojos.Customer;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/check_out")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		try (PrintWriter pw = response.getWriter()) {

			HttpSession hs = request.getSession();

			System.out.println("From checkout page session is new " + hs.isNew()); // false when cookies enabled

			System.out.println("session id " + hs.getId()); // same if cookies enabled

			Customer c = (Customer) hs.getAttribute("user_details");

			if (c != null) {

				pw.print("<h5>Hello, : " + c.getName() + "</h5>");

				// get cart from session
				ArrayList<Integer> cart = (ArrayList<Integer>) hs.getAttribute("shopping_cart");

				pw.print("<h4>Cart Contents<h4>");

				// get book dao instance from hs
				BookDaoImpl bookDao = (BookDaoImpl) hs.getAttribute("book_dao");

				// invoke dao method to get cart book details, for the book id present in the
				// cart

				double totalPrice = 0;

				for (int i : cart) {
					Book b = bookDao.getBookDetailsById(i);
					pw.print("<h5>" + b + "</h5>");
					totalPrice += b.getPrice();
				}

				pw.print("<h5>Cart Total Value " + totalPrice + "</h5>");

			} else {
				pw.print("<h5>No Cookies : Session tracking failed!!!!</h5>");
			}

			// invalidate the session
			hs.invalidate();

			pw.print("<h5>You have logged out..</h5>");

			// add visit again link
			pw.print("<h5><a href='index.html'>Visit Again</a></h5>");

		} catch (Exception e) {
			throw new ServletException("Error in do-get : " + getClass().getName(), e);
		}
	}

}
