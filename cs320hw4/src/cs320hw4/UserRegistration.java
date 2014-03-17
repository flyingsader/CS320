package cs320hw4;

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
		request.setAttribute("errorMessage", "");
		request.getRequestDispatcher("/WEB-INF/UserRegistration.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<User> users = Database.GetAllUsers();
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			String password2 = request.getParameter("retypePassword");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String errorMessage = "";
			boolean valid = true;

			if (name.trim() == "" || password.trim() == ""
					|| password2.trim() == "") {
				// Display "Required fields blank" error message
				errorMessage = "One or more of the required fields are blank";
				valid = false;
			} else if (name.length() < 4) {
				// Display "Username is less than 4 characters long" error
				// message
				errorMessage = "Username is less than 4 characters long";
				valid = false;
			} else if (userExists(users, name)) {
				// Display "Username already exists" error message
				errorMessage = "Username already exists";
				valid = false;
			} else if (password.compareTo(password2) != 0) {
				// Display "Passwords do not match" error message
				errorMessage = "Passwords do not match";
				valid = false;
			} else if (password.length() < 4) {
				// Display "Password is less than 4 character long" error
				// message
				errorMessage = "Password is less than 4 characters long";
				valid = false;
			} else {
				// No errors -- process registration
				User newUser = new User(name, password, firstName, lastName);
				Database.AddUser(newUser);
				response.sendRedirect("Login");
				return;
			}
			request.setAttribute("errorMessage", errorMessage
					+ ". Please re-enter your information.");
		} catch (Exception ex) {

		}

		doGet(request, response);
	}

	private boolean userExists(List<User> users, String username) {
		for (User u : users) {
			if (u.getUserName().compareTo(username) == 0)
				return true;
		}
		return false;
	}

}
