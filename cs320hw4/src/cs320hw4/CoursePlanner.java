package cs320hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
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
		try {
			request.setAttribute("courses", Database.GetAllCourses());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(request.getAttribute("courses") == null)
			request.setAttribute("courses", new ArrayList<Course>());
		}
		request.getRequestDispatcher("/WEB-INF/CoursePlanner.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
