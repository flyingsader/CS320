package cs320final;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddPosition")
public class AddPosition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddPosition() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			request.setAttribute("jobPositions", Database.GetAllJobPositions());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (request.getAttribute("jobPositions") == null)
				request.setAttribute("jobPositions", new ArrayList<Job>());
		}

		request.getRequestDispatcher("/WEB-INF/AddPosition.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String position = request.getParameter("position");
			Database.AddJobPosition(new Job(position));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		doGet(request, response);
	}

}
