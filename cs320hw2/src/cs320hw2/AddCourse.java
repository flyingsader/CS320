package cs320hw2;

import java.io.IOException;
import java.io.PrintWriter;
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
		if(request.getSession().getAttribute("username") == null)
			response.sendRedirect("Login");

		PrintWriter writer = response.getWriter();
		HashMap<String, Course> courses = (HashMap<String, Course>) getServletContext()
				.getAttribute("courses");

		// create form
		writer.write("<html><head><title>Add Course</title></head><body>");
		writer.write("<form action='AddCourse' method='post'>");
		writer.write("<a href='Login'>Login/Logout</a><br/>");
		writer.write("<table>");
		// Code
		writer.write("<tr><td>Code:</td><td><input name='code' /></td></tr>");
		// Title
		writer.write("<tr><td>Title:</td><td><input name='title' /></td></tr>");
		// Prerequisites
		writer.write("<tr><td style='vertical-align:top'>Prerequisites:</td><td><ul>");

		for (String key : courses.keySet()) {
			writer.write("<li>" + key
					+ "<input name='prereq' type='checkbox' value='" + key
					+ "' /></li>");
		}

		writer.write("</ul></td></tr>");
		// Button
		writer.write("<tr><td colspan='2'><input type='submit' name='add' value='Add' />");
		writer.write("</form></body></html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// grab courses from context
		HashMap<String, Course> courses = (HashMap<String, Course>) getServletContext()
				.getAttribute("courses");

		// pull values from form
		String code = request.getParameter("code");
		String name = request.getParameter("title");
		String[] prereq = request.getParameterValues("prereq");
		ArrayList<Course> reqs = new ArrayList<Course>();

		if (prereq != null)
			for (String req : prereq) {
				reqs.add(courses.get(req));
			}
		// create new course
		Course c = new Course(code, name, reqs);

		// add course to context
		courses.put(c.getCode(), c);

		// redirect back to CoursePlanner
		response.sendRedirect("CoursePlanner");
	}

}
