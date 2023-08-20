package pages;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class StatusServlet
 */
@WebServlet("/status")
public class StatusServlet extends HttpServlet {
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

			Voter voter = (Voter) session.getAttribute("user_details");

			if (voter.isStatus() == false) {

				VoterDaoImpl voterDao = (VoterDaoImpl) session.getAttribute("voter_dao");

				CandidateDaoImpl candidateDao = (CandidateDaoImpl) session.getAttribute("candidate_dao");

				pw.print("<h5>Hello " + voter.getName() + "<h5>");

				String candidateId = request.getParameter("cand_list");

				System.out.println(candidateDao.incrementVotes(Integer.parseInt(candidateId)));

				System.out.println(voterDao.updateVotingStatus(voter.getId()));

				pw.print("<h5>You have voted successfully!!<h5>");

			} else {
				pw.print("<h5>You have already voted !! <h5>");
			}

			pw.print("<h5><a href='login.html'>Logout</a></h5>");

			session.invalidate();

		} catch (Exception e) {
			throw new ServletException("Error in " + getClass().getName(), e);
		}
	}
}
