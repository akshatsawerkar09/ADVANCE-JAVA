package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-post : " + getClass().getName());
		// set content type
		response.setContentType("text/html");
		// get writer
		try (PrintWriter pw = response.getWriter()) {

			pw.print("<h5>Successful Login from Category Page....<h5>");

			// get validated client details from request scope
			Customer customer = (Customer) request.getAttribute("user_details");

			if (customer != null) {
				pw.print("<h5>Customer Details from request : " + customer + "</h5>");
			} else {
				pw.print("<h5>Request Dispacthing failed !!!! <h5>");
			}

		}

	}

}
