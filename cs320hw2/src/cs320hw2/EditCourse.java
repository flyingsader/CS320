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

@WebServlet("/EditCourse")
public class EditCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditCourse() {
		super();
	}

	protected Course getCourse(int id) {
		HashMap<String, Course> courses = (HashMap<String, Course>) getServletContext()
				.getAttribute("courses");
		for (String s : courses.keySet())
			if (courses.get(s).getId() == id)
				return courses.get(s);

		return null;
	}

	protected boolean isPrereq(Course c, String req) {
		return c.getPrerequisitesList().contains(req);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// check to see if user is logged in
		if(request.getSession().getAttribute("username") == null)
			response.sendRedirect("Login");
		
		PrintWriter writer = response.getWriter();
		HashMap<String, Course> courses = (HashMap<String, Course>) getServletContext()
				.getAttribute("courses");
		if (!request.getQueryString().contains("id"))
			response.sendRedirect("CoursePlanner");
		String editing = request.getParameter("id");
		Course course = getCourse(Integer.valueOf(editing));

		// create form
		writer.write("<html><head><title>Edit Course</title></head><body>");
		writer.write("<form action='EditCourse' method='post'>");
		writer.write("<input type='hidden' name='id' value='" + editing
				+ "' />");
		writer.write("<a href='Login'>Login/Logout</a><br/>");
		writer.write("<table>");
		// Code
		writer.write("<tr><td>Code:</td><td><input name='code' value='"
				+ course.getCode() + "' /></td></tr>");
		// Title
		writer.write("<tr><td>Title:</td><td><input name='title' value='"
				+ course.getName() + "' /></td></tr>");
		// Prerequisites
		writer.write("<tr><td style='vertical-align:top'>Prerequisites:</td><td><ul>");

		for (String key : courses.keySet()) {
			if (!key.equalsIgnoreCase(course.getCode()))
				writer.write("<li>" + key
						+ "<input name='prereq' type='checkbox' value='" + key
						+ "'"
						+ (isPrereq(course, key) ? " checked='true' " : "")
						+ "</li>");
		}

		writer.write("</ul></td></tr>");
		// Button
		writer.write("<tr><td colspan='2'><input type='submit' name='edit' value='Save' />");
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
		int editing = Integer.valueOf(request.getParameter("id"));
		ArrayList<Course> reqs = new ArrayList<Course>();

		if (prereq != null)
			for (String req : prereq)
				reqs.add(courses.get(req));

		// get course object
		Course c = getCourse(editing);

		// remove previous key (in case key changes)
		courses.remove(c.getCode());
		
		// update course information
		c.setCode(code);
		c.setName(name);
		c.setPrereqs(reqs);
		
		courses.put(c.getCode(), c);

		// redirect back to CoursePlanner
		response.sendRedirect("CoursePlanner");
	}

}
