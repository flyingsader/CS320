package cs320midterm;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CompleteTask")
public class CompleteTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CompleteTask() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Task> current = (ArrayList<Task>) getServletContext()
				.getAttribute("currentTasks");
		ArrayList<Task> complete = (ArrayList<Task>) getServletContext()
				.getAttribute("completedTasks");

		if (request.getParameter("id") == null) {
		} else {
			int id = Integer.valueOf(request.getParameter("id"));
			Task t = getTask(current, id);

			if (t != null) {
				current.remove(t);
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				t.setCompletionDate(format.format(Calendar.getInstance()
						.getTime()));
				complete.add(t);
			}
		}
		response.sendRedirect("Tasks");
	}

	private Task getTask(ArrayList<Task> tasks, int id) {
		for (Task t : tasks) {
			if (t.getId() == id)
				return t;
		}
		return null;
	}
}
