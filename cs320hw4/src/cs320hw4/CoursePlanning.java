package cs320hw4;

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

		try {
			request.setAttribute("courses", Database.GetAllCourses());
			if (init == null || init.length() == 0) {
				request.getSession().setAttribute("cp", new CoursePlan());
				request.getSession().setAttribute("promptText",
						"Please select the courses you have already taken:");
				request.getSession().setAttribute("availableCourses",
						Database.GetAllCourses());
				request.getSession().setAttribute("takenCourses",
						new ArrayList<Course>());
				request.getSession().setAttribute("init", "init");
			} else {

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (request.getAttribute("courses") == null)
				request.setAttribute("courses", new ArrayList<Course>());
		}

		request.getRequestDispatcher("/WEB-INF/CoursePlanning.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			CoursePlan plan = (CoursePlan) request.getSession().getAttribute(
					"cp");
			String[] selected = request.getParameterValues("selectedCourses");
			ArrayList<Course> available = (ArrayList<Course>) request
					.getSession().getAttribute("availableCourses");
			ArrayList<Course> allCourses = (ArrayList<Course>) Database
					.GetAllCourses();
			ArrayList<Course> takenCourses = (ArrayList<Course>) request
					.getSession().getAttribute("takenCourses");

			QuarterPlan qPlan = new QuarterPlan(plan.getCurrentQuarterString());

			if (selected != null && selected.length > 0)
				// Add to courses taken and quarter/course plan
				for (String s : selected) {
					Course c = Database.GetCourseByCode(s);
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
				boolean takenCourse = false;
				for (Course c2 : takenCourses) {
					if (c.getCode().equalsIgnoreCase(c2.getCode())) {
						takenCourse = true;
					}
				}
				if (!takenCourse) {
					boolean completedReqs = true;
					// Completed all prereqs
					for (Course req : Database
							.GetCoursePrerequisites(c.getId())) {
						boolean found = false;

						for (Course c3 : takenCourses) {
							if (c3.getCode().equalsIgnoreCase(req.getCode())) {
								found = true;
								break;
							}
						}

						if (!found) {
							completedReqs = false;
							break;
						}
					}

					if (completedReqs)
						available.add(c);
				}
			}

			if (available.size() == 0) {
				request.getSession().setAttribute("finish", "finish");
				request.getSession().setAttribute("promptText",
						"Here is your course plan:");
			} else {
				// Progress Quarter
				plan.nextQuarter();

				// Set prompt text
				request.getSession().setAttribute(
						"promptText",
						"You may take the following courses in <u>"
								+ plan.getCurrentQuarterString() + "</u>:");
			}
			
			// Checking if finished
			String finished = request.getParameter("finish");
			finished = (finished == null) ? (String) request
					.getAttribute("finish") : finished;
			if (finished != null) {
				request.getSession().setAttribute("promptText",
						"Here is your course plan:");
				request.getSession().setAttribute("finish", "finish");
			}

			doGet(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
