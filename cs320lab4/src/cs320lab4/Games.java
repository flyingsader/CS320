package cs320lab4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Games", loadOnStartup = 2)
public class Games extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Games() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("players", new ArrayList<String>());
		getServletContext().setAttribute("games", new ArrayList<Game>());
		loadPlayers();
		loadGames();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Games.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	private void loadPlayers() {
		List<String> players = (ArrayList<String>) getServletContext()
				.getAttribute("players");
		players.add("Blake");
		players.add("Nash");
		players.add("Kaman");
	}
	
	private void loadGames()
	{
		List<Game> games = (ArrayList<Game>)getServletContext().getAttribute("games");
		games.add(new Game("02/04/2014", "Timberwolves", "LA"));
		games.add(new Game("02/05/2014", "Cavaliers", "Cleveland"));
		games.add(new Game("02/07/2014", "76ers", "Philadelphia"));
	}
}
