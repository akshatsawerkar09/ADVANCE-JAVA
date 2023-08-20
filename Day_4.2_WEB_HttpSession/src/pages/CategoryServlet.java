package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			pw.print("<h5>Successful Login from Category Page....<h5>");

			// get HS from WC rets existing session object
			HttpSession hs = request.getSession();

			System.out.println("From category page session is new " + hs.isNew()); // false when cookies enabled

			System.out.println("session id " + hs.getId()); // same session id when cookies enabled

			// get validated client details from session scope
			Customer customer = (Customer) hs.getAttribute("user_details");

			if (customer != null) {
				pw.print("<h5>Customer Details from Session : " + customer + "</h5>");
			} else {
				pw.print("<h5>No Cookies : Session Tracking Fails!!!!!");
			}

			// add a checkout link
			pw.print("<h5><a href='check_out'>Check Out</a></h5>");

		}

	}

}
