package cs320hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/CoursePlanner", loadOnStartup = 2)
public class CoursePlanner extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoursePlanner() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("courses", new HashMap<String, Course>());
		getServletContext().setAttribute("users", new ArrayList<User>());
		loadCourses();
		loadUsers();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		HashMap<String, Course> courses = (HashMap<String, Course>) getServletContext()
				.getAttribute("courses");

		writer.write("<html><head><title>Course Planner</title></head><body>");
		writer.write("<style>table, th, td { border: 1px solid black; border-collapse: collapse; padding:5px }</style>");
		writer.write("<a href='Login'>Login/Logout</a><br/>");
		writer.write("<table><tr><th>Code</th><th>Title</th><th>Prerequisites</th><th>Operation</th></tr>");

		for (String key: courses.keySet()) {
			Course c = courses.get(key);
			writer.write("<tr><td>"
					+ c.getCode()
					+ "</td><td>"
					+ c.getName()
					+ "</td><td>" + c.getPrerequisitesList() + " </td><td><a href='EditCourse?id=" + c.getId() + "'>Edit</td></tr>");
		}

		writer.write("</table><br/>");
		writer.write("<a href='AddCourse'>Add Course</a></body></html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	private void loadCourses() {
		HashMap<String, Course> courses = (HashMap<String, Course>) getServletContext()
				.getAttribute("courses");
		if (courses == null) {
			getServletContext()
					.setAttribute("courses", new HashMap<String, Course>());
			courses = (HashMap<String, Course>) getServletContext().getAttribute(
					"courses");
		}

		try {
			File input = new File(getServletContext().getRealPath(
					"/WEB-INF/undergrad_courses.txt"));
			Scanner sc = new Scanner(input, "ISO8859-1");

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.startsWith("#") || line.trim().length() == 0)
					continue;
				String[] parts = line.split(",");

				// [code],[name](,[prereq1 .. prereqN])?
				String[] prereqs = (parts.length > 2) ? parts[2].split(" ")
						: new String[0];
				List<Course> coursePreReqs = new ArrayList<Course>();

				for (String i : prereqs) {
					coursePreReqs.add(courses.get(i));
				}

				courses.put(parts[0], new Course(parts[0], parts[1], coursePreReqs));
			}
			sc.close();
		} catch (FileNotFoundException fnfEx) {
			System.out.print(fnfEx.getMessage());
			fnfEx.printStackTrace();
		}
	}
	
	private void loadUsers()
	{
		List<User> users = (ArrayList<User>)getServletContext().getAttribute("users");
		
		// cysun
		User cysun = new User("cysun", "abcd", "", "");
		
		// cs320stu31
		User cs320stu31 = new User("cs320stu31", "abcd", "", "");
		
		users.add(cysun);
		users.add(cs320stu31);
	}
}
