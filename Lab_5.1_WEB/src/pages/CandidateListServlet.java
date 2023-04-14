package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import pojos.Candidate;
import pojos.Voter;

/**
 * Servlet implementation class CandidateListServlet
 */
@WebServlet("/candidate_list")
public class CandidateListServlet extends HttpServlet {
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

			pw.print("<h5>Hello " + voter.getName() + "</h5>");

			CandidateDaoImpl candidateDao = (CandidateDaoImpl) session.getAttribute("candidate_dao");

			List<Candidate> candidates = candidateDao.listCandidates();

			if (candidates != null) {

				pw.print("<form action='status'>");

				for (Candidate c : candidates) {
					pw.print("<input type='radio' name='cand_list' value=" + c.getId() + ">" + c.getName() + "<br>");
				}

				pw.print("<br>");

				pw.print("<input type='submit' value='VOTE'>");

				pw.print("</form>");

			} else {
				pw.print("<h5>Candidate List is null</h5>");
			}

		} catch (Exception e) {
			throw new ServletException("Error in DoGet : " + getClass().getName(), e);
		}

	}
}
