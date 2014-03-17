package cs320final;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JobApplications")
public class JobApplications extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JobApplications() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String sort = request.getParameter("sort");
			if(sort == null)
				sort = "job_title";
			request.setAttribute("jobApplications", Database.GetAllJobApplications(sort));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(request.getAttribute("jobApplications") == null)
				request.setAttribute("jobApplications", new ArrayList<JobApplication>());
		}
		request.getRequestDispatcher("/WEB-INF/JobApplications.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
