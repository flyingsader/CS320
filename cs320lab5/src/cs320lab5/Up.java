package cs320lab5;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Up")
public class Up extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Up() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String sRanking = request.getParameter("ranking");
		if(sRanking.length() == 0)
			response.sendRedirect("Songs");
		
		int ranking = Integer.valueOf(sRanking);
		Song song = getSongByRanking(ranking);
		
		if(song != null && ranking != 1)
		{
			Song prevRanking = getSongByRanking(ranking - 1);
			prevRanking.setRanking(ranking);
			song.setRanking(ranking - 1);
		}
		
		response.sendRedirect("Songs");
	}
	
	private Song getSongByRanking(int ranking)
	{
		ArrayList<Song> songs = (ArrayList<Song>)getServletContext().getAttribute("songs");
		Song song = null;
		
		for(Song s: songs)
		{
			if(s.getRanking() == ranking)
			{
				song = s;
				break;
			}
		}
		
		return song;
	}
}
