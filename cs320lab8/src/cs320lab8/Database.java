package cs320lab8;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

	private static String _url = "jdbc:mysql://localhost/cs320stu10";
	private static String _username = "cs320stu10";
	private static String _password = "VYCf*dtA";

	public static List<WeekDay> GetWeekdays() throws SQLException {
		List<WeekDay> weekdays = new ArrayList<WeekDay>();
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from day_of_week";

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				WeekDay day = new WeekDay(rs.getInt("id"), rs.getString("day"));
				weekdays.add(day);
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return weekdays;
	}

	public static List<TimeDay> GetTimedays() throws SQLException {
		List<TimeDay> timedays = new ArrayList<TimeDay>();
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from time_of_day";

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				TimeDay time = new TimeDay(rs.getInt("id"),
						rs.getString("time"));
				timedays.add(time);
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return timedays;
	}

	public static List<Reservation> GetReservations() throws SQLException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from reservations";

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				int dayId = rs.getInt("day_id");
				int timeId = rs.getInt("time_id");
				Reservation r = new Reservation(rs.getInt("id"), new WeekDay(
						dayId, ""), new TimeDay(timeId, ""),
						rs.getString("reserved_by"));
				reservations.add(r);
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return reservations;
	}

	public static Reservation GetReservation(WeekDay day, TimeDay time) throws SQLException
	{
		Reservation res = null;
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			String command = "select * from reservations where day_id = " + day.getId() + " and time_id = " + time.getId();
			Statement select = db.createStatement();
			ResultSet rs = select.executeQuery(command);

			if (rs.next()) {
				int dayId = rs.getInt("day_id");
				int timeId = rs.getInt("time_id");
				res = new Reservation(rs.getInt("id"), new WeekDay(
						dayId, ""), new TimeDay(timeId, ""),
						rs.getString("reserved_by"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}
		
		return res;
	}
	
	public static boolean AddReservation(Reservation reservation) throws SQLException {
		Connection db = null;
		boolean success = true;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			String command = "insert into reservations (day_id, time_id, reserved_by) values (?, ?, ?);";
			PreparedStatement insert = db.prepareStatement(command);
			insert.setInt(1, reservation.getDay().getId());
			insert.setInt(2, reservation.getTime().getId());
			insert.setString(3, reservation.getReservedBy());
			insert.executeUpdate();
		} catch (Exception ex) {
			success = false;
		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}
		return success;
	}

	public static boolean DeleteReservation(Reservation reservation) throws SQLException
	{
		Connection db = null;
		boolean success = true;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			String command = "delete from reservations where id = ?;";
			PreparedStatement insert = db.prepareStatement(command);
			insert.setInt(1, reservation.getId());
			insert.executeUpdate();
		} catch (Exception ex) {
			success = false;
		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}
		return success;
	}
}
