package cs320hw4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddCourse")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCourse() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// check to see if user is logged in
		if (request.getSession().getAttribute("username") == null)
		{
			response.sendRedirect("Login");
			return;
		}

		try {
			request.setAttribute("courses", Database.GetAllCourses());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(request.getAttribute("courses") == null)
				request.setAttribute("courses", new ArrayList<Course>());
		}
		request.getRequestDispatcher("/WEB-INF/AddCourse.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// pull values from form
		String code = request.getParameter("code");
		String name = request.getParameter("title");
		String[] prereq = request.getParameterValues("prereq");
		ArrayList<Course> reqs = new ArrayList<Course>();

		try
		{
		if (prereq != null)
			for (String req : prereq) {
				reqs.add(Database.GetCourseById(Integer.valueOf(req)));
			}
		// create new course
		Course c = new Course(code, name, reqs);

		// add course to context
		Database.AddCourse(c);
		}
		catch(Exception ex)
		{
			
		}
		
		// redirect back to CoursePlanner
		response.sendRedirect("CoursePlanner");
	}

}
