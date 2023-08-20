package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDaoImpl;
import pojos.Customer;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set content type
		response.setContentType("text/html");
		// get writer
		try (PrintWriter pw = response.getWriter()) {

			// get HS from WC rets existing session object
			HttpSession hs = request.getSession();

			// get validated client details from session scope
			Customer customer = (Customer) hs.getAttribute("user_details");

			if (customer != null) {
				pw.print("<h5>Hello : " + customer.getName() + "</h5>");
				// get book dao instance from session scope
				BookDaoImpl dao = (BookDaoImpl) hs.getAttribute("book_dao");

				// invoke book dao method for fetching distinct categories from db
				List<String> categories = dao.getAllCategories();

				// dynamic form generation
				pw.print("<form action='category_details'>");

				pw.print("Choose Category");

				pw.print("<select name='cat'>");

				for (String s : categories) {
					pw.print("<option value = " + s + ">" + s + "</option>");
				}
				pw.print("</select><br>");

				pw.print("<input type='submit' value='Choose'>");

				pw.print("</form>");

			} else {
				pw.print("<h5>No Cookies : Session Tracking Fails!!!!!");
			}

			// add a checkout link
			pw.print("<h5><a href='check_out'>Check Out</a></h5>");

		} catch (Exception e) {
			throw new ServletException("Error in DoGet : " + getClass().getName(), e);
		}

	}

}
