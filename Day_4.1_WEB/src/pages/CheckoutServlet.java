package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

			Cookie[] cookies = request.getCookies();

			if (cookies != null) {

				for (Cookie c : cookies) {
					if (c.getName().equals("clnt_details")) {
						pw.print("<h5>Customer Details from check out page : " + c.getValue() + "</h5>");

					}
				}

			} else {
				pw.print("<h5>No Cookies : Session tracking failed!!!!</h5>");
			}

			pw.print("<h5>You have logged out..</h5>");

			// add visit again link
			pw.print("<h5><a href='index.html'>Visit Again</a></h5>");

		}
	}

}
