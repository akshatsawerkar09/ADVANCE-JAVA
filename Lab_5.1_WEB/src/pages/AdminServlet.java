package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import pojos.Candidate;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {

			HttpSession session = request.getSession();

			CandidateDaoImpl candidateDao = (CandidateDaoImpl) session.getAttribute("candidate_dao");

			List<Candidate> candidates = candidateDao.top2Analysis();

			pw.print("<h5>Top 2 Candidates</h5>");

			for (Candidate c : candidates) {
				pw.print("Name: " + c.getName() + " Votes: " + c.getVotes() + "<br>");
			}

			LinkedHashMap<String, Integer> map = candidateDao.partyWiseAnalysis();

			pw.print("<h5>Parties with Votes</h5>");

			for (Map.Entry<String, Integer> me : map.entrySet()) {
				pw.print("Party Name: " + me.getKey() + " Votes: " + me.getValue() + "<br>");
			}

			pw.print("<h5><a href='login.html'>Log Out</a></h5>");

			session.invalidate();

		} catch (Exception e) {
			throw new ServletException("Error in doGet : " + getClass().getName(), e);
		}
	}

}
