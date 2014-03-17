package cs320lab8;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteReservation")
public class DeleteReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteReservation() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			if (request.getParameter("id") != null) {
				int id = Integer.valueOf(request.getParameter("id"));
				Reservation r = new Reservation(id, new WeekDay(-1, ""),
						new TimeDay(-1, ""), "");
				Database.DeleteReservation(r);
			}
			response.sendRedirect("Reservations");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
