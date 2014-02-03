package cs320hw2;

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
			// logout and redirect
			response.sendRedirect("CoursePlanner");
		}

		PrintWriter writer = response.getWriter();

		// create form
		writer.write("<html><head><title>Login</title></head><body>");
		writer.write("<form action='Login' method='post'>");
		writer.write("<a href='Login'>Login/Logout</a><br/>");
		writer.write("<table>");
		// UserName
		writer.write("<tr><td>UserName:</td><td><input name='loginname' /></td></tr>");
		// Password
		writer.write("<tr><td>Password:</td><td><input name='loginpassword' type='password' /></td></tr>");
		// Button
		writer.write("<tr><td><input type='submit' name='login' value='Login' /></td>");
		writer.write("<td><a href='UserRegistration'>Register</a></td>");
		writer.write("</tr></table></form></body></html>");
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
					request.getSession().setAttribute("username", name);
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
