package cs320lab4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Stats")
public class Stats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Stats() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("id"));
		request.setAttribute("game", getGame(id));
		
		request.getRequestDispatcher("/WEB-INF/Stats.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String player = request.getParameter("player");
		int points = Integer.valueOf(request.getParameter("statPoints"));
		int rebounds = Integer.valueOf(request.getParameter("statRebounds"));
		int assists = Integer.valueOf(request.getParameter("statAssists"));
		int id = Integer.valueOf(request.getParameter("id"));
		
		Game g = getGame(id);
		g.getStats().add(new Stat(player, points, rebounds, assists));
		
		doGet(request,response);		
	}
	
	private Game getGame(int id)
	{
		List<Game> games = (ArrayList<Game>)getServletContext().getAttribute("games");
		
		for(Game g: games)
		{
			if(g.getId() == id)
			{
				return g;
			}
		}
		return null;
	}

}
