package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDaoImpl;
import pojos.Customer;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet(value = "/validate", loadOnStartup = 1)
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDaoImpl customerDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		/*
		 * // create dao instance CustomerDaoImpl customerDao = new CustomerDaoImpl();
		 * creating customerDao as a method local variable will not be useful because
		 * destroy will not be able to clean up and dopost will not be able to call crud
		 * method so we have to create instance var
		 */
		try {
			customerDao = new CustomerDaoImpl(); // we cannot use throws Exception because overiding form of method
													// cannot throw new or broader checked exception
		} catch (Exception e) {
			// e.printStackTrace(); if genuine error occurs WC will not be able to something
			// went wrong because we have absorbed all the exception and it will assume init
			// succeed and continue life cycle
			throw new ServletException("Error in init : " + getClass().getName(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			customerDao.cleanUp();
		} catch (SQLException e) {
			throw new RuntimeException("Error in Destroy : " + getClass().getName(), e);
		}
	}

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

			// read request data : em, pass from client request
			// API of ServletRequest public String getParameter(String name)
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			System.out.println("In do-post " + email + " " + password);
			// invoke dao method for auth
			Customer customer = customerDao.authenticateUser(email, password);

			if (customer == null) {
				// failed login : send a mesg n retry link
				pw.print("<h5>Invalid Login, Please <a href='login.html'>Retry</a></h5>");
			} else {
				// here we changed session to request scope because we are changing client pull
				// to server pull
				// how to save validated client details under (session*) request scope
				request.setAttribute("user_details", customer);

				pw.print("<h5>Successful Login from Authentication Servlet</h5>");

				// pw.flush(); here it will not affect

				// user server pull to navigate client to the category page in the same request
				// managed by Wc in server space

				// API of servletRequest
				// public RequestDispatcher getRequestDispatacher(String path);
				// 1
				RequestDispatcher rd = request.getRequestDispatcher("category");

				System.out.println("RD : " + rd.getClass().getName());

				// 2 : rd's method
				// public void include(ServletRequest rq, ServletResponse rs)
				rd.include(request, response);

				System.out.println("came back in do-post : " + getClass().getName());

				pw.print("<h5>adding the contents after include</h5>");
			}

		} catch (Exception e) {
			throw new ServletException("Error in DoPost : " + getClass().getName(), e);
		}

	}

}
