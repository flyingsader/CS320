package cs320final;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Apply")
public class Apply extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Apply() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("jobPositions", Database.GetAllJobPositions());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(request.getAttribute("jobPositions") == null)
				request.setAttribute("jobPositions", new ArrayList<Job>());
		}
		request.getRequestDispatcher("/WEB-INF/Apply.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String applicant = request.getParameter("applicant");
			String[] positions = request.getParameterValues("positions");

			if (positions != null) {
				for (String pos : positions) {
					Database.AddJobApplication(new JobApplication(pos,
							applicant));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		response.sendRedirect("JobApplications");
	}

}
