package cs320lab4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddGame")
public class AddGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddGame() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/AddGame.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("gameDate");
		String opponent = request.getParameter("gameOpponent");
		String location = request.getParameter("gameLocation");
		
		List<Game> games = (ArrayList<Game>)getServletContext().getAttribute("games");
		
		games.add(new Game(date,opponent,location));
		response.sendRedirect("Games");
	}

}
