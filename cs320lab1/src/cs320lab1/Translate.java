package cs320lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Translate")
public class Translate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Translate() {
        super();
    }
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		getServletContext().setAttribute("dictionary", new HashMap<String, List<String>>());
		loadDictionaryFile();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.print("<html><head><title>CS320Stu10 Translate Servlet</title></head><body>");
		writer.print("<form action='Translate' method='post'>");
		writer.print("<p>Please enter an English word: <input type='text' name='english' /> <input type='submit' name='translate' value='Translate' /></p>");
		writer.print("</form></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String word = request.getParameter("english");
		Map<String, List<String>> map = (HashMap<String, List<String>>)getServletContext().getAttribute("dictionary");
		PrintWriter writer = response.getWriter();
		writer.print("<html><head><title>CS320Stu10 Translations Servlet</title></head><body>");
		writer.print("English: " + word + "<br/>");
		writer.print("Spanish: <br/>");
		
		if(map.containsKey(word))
		{
			writer.print("<ol>");
			List<String> words = map.get(word);
			for(int i = 0; i < words.size(); i++)
			{
				writer.print("<li>" + words.get(i) + "</li>");
			}
			writer.print("</ol>");
		}
		else
		{
			writer.print("<p>No translations found.</p>");
		}
		
		writer.print("<br/><a href='Translate'>Translate Another Word</a>");
		writer.print("</body></html>");	
	}
	
	private void loadDictionaryFile() {
		@SuppressWarnings("unchecked")
		Map<String, List<String>> map = (HashMap<String, List<String>>)getServletContext().getAttribute("dictionary");
		
		try
		{
			File input = new File(getServletContext().getRealPath("/WEB-INF/dictionary.txt"));
			Scanner sc = new Scanner(input , "ISO8859-1");
		
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				if(line.startsWith("#"))
					continue;
				String[] parts = line.split("\t");
				String english = parts[0];

				List<String> list = (map.containsKey(english)) ? map.get(english) : new ArrayList<String>();
				
				for(int i = 1; i < parts.length; i++)
					list.add(parts[i]);
				
				if(!map.containsKey(english))
					map.put(english, list);
			}
			sc.close();
		}
		catch(FileNotFoundException fnfEx)
		{
			System.out.print(fnfEx.getMessage());
			fnfEx.printStackTrace();
		}
	}

}
