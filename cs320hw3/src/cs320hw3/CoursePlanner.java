package cs320hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		getServletContext().setAttribute("courses",
				new ArrayList<Course>());
		getServletContext().setAttribute("users", new ArrayList<User>());
		loadCourses();
		loadUsers();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		CoursePlan plan = (CoursePlan)request.getSession().getAttribute("cp");
		if(plan != null)
		{
			request.getSession().removeAttribute("cp");
			request.getSession().removeAttribute("init");
			request.getSession().removeAttribute("finish");
		}
		
		if(request.getSession().getAttribute("loginText") == null || request.getSession().getAttribute("username") == null)
			request.getSession().setAttribute("loginText", "Login");
		
		ArrayList<Course> courses = (ArrayList<Course>)getServletContext().getAttribute("courses");
		Collections.sort(courses, new Comparator<Course>(){
			@Override
			public int compare(Course a, Course b) {
				return a.getCode().compareToIgnoreCase(b.getCode());
			}
		});
		request.getRequestDispatcher("/WEB-INF/CoursePlanner.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void loadCourses() {
		ArrayList<Course> courses = (ArrayList<Course>) getServletContext()
				.getAttribute("courses");
		if (courses == null) {
			getServletContext().setAttribute("courses",
					new ArrayList<Course>());
			courses = (ArrayList<Course>) getServletContext()
					.getAttribute("courses");
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
					for(int j = 0; j < courses.size(); j++)
					{
						if(courses.get(j).getCode().equals(i))
						{
							coursePreReqs.add(courses.get(j));
							break;
						}
					}
				}

				courses.add(new Course(parts[0], parts[1],
						coursePreReqs));
			}
			sc.close();
		} catch (FileNotFoundException fnfEx) {
			System.out.print(fnfEx.getMessage());
			fnfEx.printStackTrace();
		}
	}

	private void loadUsers() {
		List<User> users = (ArrayList<User>) getServletContext().getAttribute(
				"users");

		// cysun
		User cysun = new User("cysun", "abcd", "", "");

		// cs320stu31
		User cs320stu31 = new User("cs320stu31", "abcd", "", "");

		users.add(cysun);
		users.add(cs320stu31);
	}
}
