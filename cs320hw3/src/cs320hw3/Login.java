package cs320hw3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// if user is logged in
		if (request.getSession().getAttribute("username") != null) {
			request.getSession().invalidate();
			request.getSession().setAttribute("loginText", "Login");
			// logout and redirect
			response.sendRedirect("CoursePlanner");
			return;
		}

		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> users = (ArrayList<User>) getServletContext().getAttribute(
				"users");
		String name = request.getParameter("loginname");
		String password = request.getParameter("loginpassword");
		String errorMessage = "";

		for (User user : users) {
			// Find user name
			if (user.getUserName().compareTo(name) == 0) {
				// compare password
				if (user.isPassword(password))
				{
					request.getSession().setAttribute("username", name);
					request.getSession().setAttribute("loginText", "Logout");
				}
				else
					errorMessage = "Incorrect password";
				break;
			}
		}

		if (request.getSession().getAttribute("username") == null) {
			if (errorMessage.trim().length() == 0)
				errorMessage = "Username is not found";
			doGet(request, response);
		} else
			// redirect
			response.sendRedirect("CoursePlanner");
	}

}
