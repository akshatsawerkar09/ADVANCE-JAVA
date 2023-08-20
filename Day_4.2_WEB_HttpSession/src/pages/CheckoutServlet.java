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
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/check_out")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		try (PrintWriter pw = response.getWriter()) {

			HttpSession hs = request.getSession();

			System.out.println("From checkout page session is new " + hs.isNew()); // false when cookies enabled

			System.out.println("session id " + hs.getId()); // same if cookies enabled

			Customer c = (Customer) hs.getAttribute("user_details");

			if (c != null) {

				pw.print("<h5>Customer Details from Session : " + c + "</h5>");

			} else {
				pw.print("<h5>No Cookies : Session tracking failed!!!!</h5>");
			}

			// invalidate the session
			hs.invalidate();

			pw.print("<h5>You have logged out..</h5>");

			// add visit again link
			pw.print("<h5><a href='index.html'>Visit Again</a></h5>");

		}
	}

}
