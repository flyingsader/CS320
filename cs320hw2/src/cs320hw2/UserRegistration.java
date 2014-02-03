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

@WebServlet("/UserRegistration")
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserRegistration() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String errorMessage = (String) request.getAttribute("errorMessage");

		// create form
		writer.write("<html><head><title>User Registration</title></head><body>");
		writer.write("<form action='UserRegistration' method='post'>");
		writer.write("<a href='Login'>Login/Logout</a><br/>");
		
		if(errorMessage != null)
		{
			writer.write("<p style='color:red'>" + errorMessage + "</p><br/>");
		}
		
		writer.write("<table>");
		// UserName
		writer.write("<tr><td>UserName:</td><td><input name='username' /></td></tr>");
		// Password
		writer.write("<tr><td>Password:</td><td><input name='password' type='password' /></td></tr>");
		// Confirm Password
		writer.write("<tr><td>Retype Password:</td><td><input name='retypePassword' type='password' /></td></tr>");
		// First Name
		writer.write("<tr><td>First Name (optional):</td><td><input name='firstName' /></td></tr>");
		// Last Name
		writer.write("<tr><td>Last Name (optional):</td><td><input name='lastName'/></td></tr>");
		// Button
		writer.write("<tr><td><input type='submit' name='register' value='Register' /></td>");
		writer.write("<td><a href='CoursePlanner'>Cancel</a></td>");
		writer.write("</tr></table></form></body></html>");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> users = (ArrayList<User>)getServletContext().getAttribute("users");
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("retypePassword");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String errorMessage = "";
		boolean valid = true;
		
		if(name.trim() == "" || password.trim() == "" || password2.trim() == "")
		{
			// Display "Required fields blank" error message
			errorMessage = "One or more of the required fields are blank";
			valid = false;
		}
		else if(name.length() < 4)
		{
			// Display "Username is less than 4 characters long" error message
			errorMessage = "Username is less than 4 characters long";
			valid = false;
		}
		else if(userExists(users, name))
		{
			// Display "Username already exists" error message
			errorMessage = "Username already exists";
			valid = false;
		}
		else if(password.compareTo(password2) != 0)
		{
			// Display "Passwords do not match" error message
			errorMessage = "Passwords do not match";
			valid = false;
		}
		else if (password.length() < 4)
		{
			// Display "Password is less than 4 character long" error message
			errorMessage = "Password is less than 4 characters long";
			valid = false;
		}
		else
		{
			// No errors -- process registration
			User newUser = new User(name, password, firstName, lastName);
			users.add(newUser);
			response.sendRedirect("Login");
		}
		
		request.setAttribute("errorMessage", errorMessage + ". Please re-enter your information.");
		doGet(request, response);
	}
	
	private boolean userExists(List<User> users, String username)
	{
		for(User u: users)
		{
			if(u.getUserName().compareTo(username) == 0)
				return true;
		}
		return false;
	}

}
