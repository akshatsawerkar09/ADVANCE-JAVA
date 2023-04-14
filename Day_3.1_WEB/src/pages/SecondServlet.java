package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("In Init123 : " + Thread.currentThread());
	}

	@Override
	public void destroy() {
		System.out.println("In Destroy123 : " + Thread.currentThread());
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("In Do-Get123 : " + Thread.currentThread());
		// set response content type
		response.setContentType("text/html");
		// get char oriented buffered o/p stream , to send response from server to
		// client
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h4>Servlet Deployed via annotations @ " + LocalDateTime.now() + "</h4>");
		}
		// after try pw.close and response is send to the client
	}
}
