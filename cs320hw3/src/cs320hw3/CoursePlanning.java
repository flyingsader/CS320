package cs320hw3;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CoursePlanning")
public class CoursePlanning extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CoursePlanning() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String init = (String) request.getSession().getAttribute("init");

		if (init == null || init.length() == 0) {
			request.getSession().setAttribute("cp", new CoursePlan());
			request.getSession().setAttribute("promptText",
					"Please select the courses you have already taken:");
			request.getSession().setAttribute(
					"availableCourses",
					((ArrayList<Course>) getServletContext().getAttribute(
							"courses")).clone());
			request.getSession().setAttribute("takenCourses",
					new ArrayList<Course>());
			request.getSession().setAttribute("init", "init");
		} else {

		}

		request.getRequestDispatcher("/WEB-INF/CoursePlanning.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		CoursePlan plan = (CoursePlan) request.getSession().getAttribute("cp");
		String[] selected = request.getParameterValues("selectedCourses");
		ArrayList<Course> available = (ArrayList<Course>) request.getSession()
				.getAttribute("availableCourses");
		ArrayList<Course> allCourses = (ArrayList<Course>) getServletContext()
				.getAttribute("courses");
		ArrayList<Course> takenCourses = (ArrayList<Course>) request
				.getSession().getAttribute("takenCourses");

		QuarterPlan qPlan = new QuarterPlan(plan.getCurrentQuarterString());

		if (selected != null && selected.length > 0)
			// Add to courses taken and quarter/course plan
			for (String s : selected) {
				Course c = getCourse(allCourses, s);
				takenCourses.add(c);

				if (allCourses.size() != available.size()) {
					qPlan.getCourses().add(c);
				}
			}

		if (allCourses.size() != available.size())
			plan.getPlan().add(qPlan);

		// Clear available courses
		available.clear();

		// Set courses available
		for (Course c : allCourses) {
			// Hasn't taken the course
			if (!takenCourses.contains(c)) {
				boolean completedReqs = true;
				// Completed all prereqs
				for (Course req : c.getPrerequisites()) {
					if (!takenCourses.contains(req)) {
						completedReqs = false;
						break;
					}
				}

				if (completedReqs)
					available.add(c);
			}
		}

		if (available.size() == 0) {
			request.setAttribute("finish", "finish");
		}
		
		// Progress Quarter
		plan.nextQuarter();

		// Set prompt text
		request.getSession().setAttribute(
				"promptText",
				"You may take the following courses in <u>"
						+ plan.getCurrentQuarterString() + "</u>:");

		// Checking if finished
		String finished = request.getParameter("finish");
		finished = (finished == null) ? (String) request.getAttribute("finish")
				: finished;
		if (finished != null) {
			request.getSession().setAttribute("promptText",
					"Here is your course plan:");
		}

		doGet(request, response);
	}

	private Course getCourse(ArrayList<Course> list, String code) {
		Course c = null;

		for (Course course : list) {
			if (course.getCode().equalsIgnoreCase(code)) {
				c = course;
				break;
			}
		}

		return c;
	}

}
