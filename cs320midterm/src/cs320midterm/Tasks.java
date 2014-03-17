package cs320midterm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Tasks", loadOnStartup = 2)
public class Tasks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Tasks() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("currentTasks", new ArrayList<Task>());
		getServletContext().setAttribute("completedTasks", new ArrayList<Task>());
		loadTasks();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/Tasks.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String taskMessage = request.getParameter("taskMessage");
		String taskDueDate = request.getParameter("taskDueDate");
		
		ArrayList<Task> tasks = (ArrayList<Task>)getServletContext().getAttribute("currentTasks");
		tasks.add(new Task(taskMessage, taskDueDate));
		
		doGet(request, response);
	}

	private void loadTasks()
	{
		ArrayList<Task> currentTasks = (ArrayList<Task>)getServletContext().getAttribute("currentTasks");
		ArrayList<Task> completedTasks = (ArrayList<Task>)getServletContext().getAttribute("completedTasks");
		
		// Pending Tasks
		currentTasks.add(new Task("Buy Grocery", "02/22/2014"));
		currentTasks.add(new Task("Watch Lego movie", "02/16/2014"));
		currentTasks.add(new Task("Meeting with Dr Pamula", "02/20/2014"));
		
		// Completed Tasks
		completedTasks.add(new Task("CS320 HW3", "02/21/2014", "02/16/2014"));
		completedTasks.add(new Task("Prepare for CS320 midterm", "02/19/2014", "02/18/2014"));
	}
}
