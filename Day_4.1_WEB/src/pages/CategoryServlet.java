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
			// get user details from a cookie
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("clnt_details")) {
						pw.print("<h5>Customer Details : " + c.getValue() + "</h5>");
						break;
					}
				}

			} else {
				pw.print("<h5>No Cookies : Session Tracking Fails!!!!!");
			}

			// add a checkout link
			pw.print("<h5><a href='check_out'>Check Out</a></h5>");

		}

	}

}
