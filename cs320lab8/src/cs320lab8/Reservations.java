package cs320lab8;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Reservations")
public class Reservations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Reservations() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("weekDays", Database.GetWeekdays());
			request.setAttribute("timeDays", Database.GetTimedays());
			request.setAttribute("reservations", Database.GetReservations());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(request.getAttribute("weekDays") == null)
				request.setAttribute("weekDays", new ArrayList<WeekDay>());
			
			if(request.getAttribute("timeDays") == null)
				request.setAttribute("timeDays", new ArrayList<TimeDay>());
			
			if(request.getAttribute("reservations") == null)
				request.setAttribute("reservations", new ArrayList<Reservation>());
		}
		request.getRequestDispatcher("/WEB-INF/Reservations.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try
		{
			String day = request.getParameter("weekDay");
			String time = request.getParameter("timeDay");
			int dayId = Integer.valueOf(day);
			int timeId = Integer.valueOf(time);
			String reserve = request.getParameter("reserveName");
			
			Reservation r = new Reservation(-1, new WeekDay(dayId,""), new TimeDay(timeId,""), reserve);
			if(Database.GetReservation(r.getDay(), r.getTime()) == null)
			{
				Database.AddReservation(r);
			}
			else
			{
				request.setAttribute("errorMessage", "That spot is already reserved.");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			request.setAttribute("errorMessage", "That spot is already reserved.");
		}
		doGet(request, response);
	}

}
