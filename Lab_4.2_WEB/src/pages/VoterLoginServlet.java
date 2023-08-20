package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.VoterDaoImpl;
import pojos.Voter;

/**
 * Servlet implementation class UserAuthenticationServlet
 */
@WebServlet(urlPatterns = "/validate", loadOnStartup = 1)
public class VoterLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoterDaoImpl voterDao;
	private CandidateDaoImpl candidateDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		try {
			voterDao = new VoterDaoImpl();
			candidateDao = new CandidateDaoImpl();
		} catch (Exception e) {
			throw new ServletException("Error in init : " + getClass().getName(), e);
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			voterDao.cleanUp();
			candidateDao.cleanUp();
		} catch (SQLException e) {
			throw new RuntimeException("Error in destroy : " + getClass().getName(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {

			String email = request.getParameter("em");

			String password = request.getParameter("pass");

			Voter voter = voterDao.validateUser(email, password);

			if (voter == null) {
				pw.print("<h5>Invalid Login <a href='login.html'></a></h5>");
			} else {

				HttpSession session = request.getSession();

				session.setAttribute("voter_dao", voterDao);

				session.setAttribute("candidate_dao", candidateDao);

				session.setAttribute("user_details", voter);

				if (voter.getRole().equalsIgnoreCase("admin")) {
					response.sendRedirect("admin");
				} else {
					if (voter.isStatus() == true) {
						response.sendRedirect("status");
					} else {
						response.sendRedirect("candidate_list");
					}
				}
			}

		} catch (Exception e) {
			throw new ServletException("Error in doPost : " + getClass().getName(), e);
		}

	}
}
