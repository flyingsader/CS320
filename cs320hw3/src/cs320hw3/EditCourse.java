package cs320hw3;

import java.io.IOException;
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

	protected Course getCourse(int id) {
		List<Course> courses = (List<Course>) getServletContext()
				.getAttribute("courses");
		for (Course s : courses)
			if (s.getId() == id)
				return s;

		return null;
	}

	protected boolean isPrereq(Course c, String req) {
		return c.getPrerequisitesList().contains(req);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// check to see if user is logged in
		if(request.getSession().getAttribute("username") == null)
		{
			response.sendRedirect("Login");
			return;
		}
		
		if (request.getParameter("id") == null)
		{
			response.sendRedirect("CoursePlanner");
			return;
		}
		
		int id = Integer.valueOf(request.getParameter("id"));
		request.setAttribute("course", getCourse(id));
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("/WEB-INF/EditCourse.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// grab courses from context
		ArrayList<Course> courses = (ArrayList<Course>) getServletContext()
				.getAttribute("courses");

		// pull values from form
		String code = request.getParameter("code");
		String name = request.getParameter("title");
		String[] prereq = request.getParameterValues("prereq");
		int editing = Integer.valueOf(request.getParameter("id"));
		ArrayList<Course> reqs = new ArrayList<Course>();

		if (prereq != null)
			for (String req : prereq)
				reqs.add(courses.get(Integer.valueOf(req)));

		// get course object
		Course c = getCourse(editing);

		// remove previous key (in case key changes)
		//courses.remove(c);
		
		// update course information
		c.setCode(code);
		c.setName(name);
		c.setPrereqs(reqs);
		//c.getPrerequisitesList().in
		
		//courses.add(c);

		// redirect back to CoursePlanner
		response.sendRedirect("CoursePlanner");
	}

}
