package cs320hw4;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditCourse() {
		super();
	}

	protected boolean isPrereq(Course c, String req) {
		return c.getPrerequisitesList().contains(req);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// check to see if user is logged in
		if (request.getSession().getAttribute("username") == null) {
			response.sendRedirect("Login");
			return;
		}

		if (request.getParameter("id") == null) {
			response.sendRedirect("CoursePlanner");
			return;
		}

		int id = Integer.valueOf(request.getParameter("id"));
		try {
			request.setAttribute("course", Database.GetCourseById(id));
			request.setAttribute("courses", Database.GetAllCourses());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (request.getAttribute("course") == null)
				response.sendRedirect("CoursePlanner");
			if(request.getAttribute("courses") == null)
				request.setAttribute("courses", new ArrayList<Course>());
		}
		request.setAttribute("id", id);
		request.getRequestDispatcher("/WEB-INF/EditCourse.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// pull values from form
		String code = request.getParameter("code");
		String name = request.getParameter("title");
		String[] prereq = request.getParameterValues("prereq");
		int editing = Integer.valueOf(request.getParameter("id"));
		ArrayList<Course> reqs = new ArrayList<Course>();
		Course c = null;

		try {
			if (prereq != null)
				for (String req : prereq)
					reqs.add(Database.GetCourseById(Integer.valueOf(req)));

			// get course object
			c = Database.GetCourseById(editing);

			// update course information
			c.setCode(code);
			c.setName(name);
			c.setPrereqs(reqs);

			Database.UpdateCourse(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (c == null)
				doGet(request, response);
		}

		// redirect back to CoursePlanner
		response.sendRedirect("CoursePlanner");
	}

}
