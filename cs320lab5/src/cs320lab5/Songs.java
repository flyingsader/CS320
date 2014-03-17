package cs320lab5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Songs", loadOnStartup = 2)
public class Songs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Songs() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("songs", new ArrayList<Song>());
		loadSongs();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Song> songs = (ArrayList<Song>)getServletContext().getAttribute("songs");
		Collections.sort(songs, new Comparator<Song>(){
			@Override
			public int compare(Song a, Song b) {
				return Integer.compare(a.getRanking(), b.getRanking());
			}
		});
		request.getRequestDispatcher("/WEB-INF/Songs.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String songName = request.getParameter("songName");
		String songArtist = request.getParameter("songArtist");
		ArrayList<Song> songs = (ArrayList<Song>)getServletContext().getAttribute("songs");
		
		songs.add(new Song(songName, songArtist));
		doGet(request, response);
	}
	
	private void loadSongs()
	{
		ArrayList<Song> songs = (ArrayList<Song>)getServletContext().getAttribute("songs");
		songs.add(new Song("Dark Horse", "Katy Perry featuring Juicy J"));
		songs.add(new Song("Drunk in Love", "Beyonce featuring Jay Z"));
		songs.add(new Song("Timber", "Pit Bull featuring Ke$ha"));
		songs.add(new Song("Talk Dirty", "Jason Derulo featuring 2 Chainz"));
		songs.add(new Song("Counting Stars", "OneRepublic"));
	}

}
