package cs320hw4;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

//	private static String _url = "jdbc:mysql://localhost/cs320hw4";
//	private static String _username = "root";
//	private static String _password = "Smalls0622!";
	
	private static String _url = "jdbc:mysql://cs3.calstatela.edu/cs320stu10";
	private static String _username = "cs320stu10";
	private static String _password = "VYCf*dtA";

	public static List<Course> GetAllCourses() throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from courses";

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				int course_id = rs.getInt("id");
				Course course = new Course(course_id,
						rs.getString("course_code"),
						rs.getString("course_title"),
						GetCoursePrerequisites(course_id));
				courses.add(course);
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return courses;
	}

	public static Course GetCourseById(int id) throws SQLException {
		Course course = null;
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from courses where id = " + id;

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				int course_id = rs.getInt("id");
				course = new Course(course_id, rs.getString("course_code"),
						rs.getString("course_title"),
						GetCoursePrerequisites(course_id));
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return course;
	}

	public static Course GetCourseByCode(String code) throws SQLException {
		Course course = null;
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from courses where course_code = '"
					+ code + "'";

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				int course_id = rs.getInt("id");
				course = new Course(course_id, rs.getString("course_code"),
						rs.getString("course_title"),
						GetCoursePrerequisites(course_id));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return course;
	}

	public static boolean AddCourse(Course course) throws SQLException {
		Connection db = null;
		boolean success = true;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			String command = "insert into courses (course_code, course_title) values (?, ?);";
			PreparedStatement insert = db.prepareStatement(command);
			insert.setString(1, course.getCode());
			insert.setString(2, course.getName());
			insert.executeUpdate();

			// At this point, we need to pull from database to get DB id, not
			// code id
			Course c = GetCourseByCode(course.getCode());
			SetCoursePrerequisites(c.getId(), course.getPrerequisites());
		} catch (Exception ex) {
			success = false;
		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}
		return success;
	}

	public static boolean UpdateCourse(Course course) throws SQLException {
		Connection db = null;
		boolean success = true;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			String command = "update courses set course_code = ?, course_title = ? where id = ?";
			PreparedStatement update = db.prepareStatement(command);
			update.setString(1, course.getCode());
			update.setString(2, course.getName());
			update.setInt(3, course.getId());
			update.executeUpdate();

			SetCoursePrerequisites(course.getId(), course.getPrerequisites());
		} catch (Exception ex) {
			success = false;
		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}
		return success;
	}

	public static List<Course> GetCoursePrerequisites(int id)
			throws SQLException {
		List<Course> courses = new ArrayList<Course>();
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from course_prerequisites where course_id = "
					+ id;

			ResultSet rs = select.executeQuery(command);

			while(rs.next()) {
				int req_id = rs.getInt("prerequisite_id");
				Statement prereq = db.createStatement();
				String preReqCommand = "select * from courses where id = "
						+ req_id;

				ResultSet req_rs = prereq.executeQuery(preReqCommand);

				if (req_rs.next()) {
					Course course = new Course(req_rs.getInt("id"),
							req_rs.getString("course_code"),
							req_rs.getString("course_title"),
							new ArrayList<Course>());
					courses.add(course);
				}
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return courses;
	}

	public static boolean SetCoursePrerequisites(int id,
			List<Course> prerequisites) throws SQLException {
		Connection db = null;
		boolean success = true;

		try {
			// need to delete all prereqs first to ensure no duplicates
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String clear = "delete from course_prerequisites where course_id = "
					+ id;
			select.executeUpdate(clear);

			String command = "insert into course_prerequisites values (?,?);";
			for (Course c : prerequisites) {
				PreparedStatement insert = db.prepareStatement(command);
				insert.setInt(1, id);
				insert.setInt(2, c.getId());
				insert.executeUpdate();
			}

		} catch (Exception ex) {
			success = false;
		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return success;
	}
	
	public static List<User> GetAllUsers() throws SQLException
	{
		List<User> users = new ArrayList<User>();
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from users";

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				int user_id = rs.getInt("id");
				User user = new User(user_id,
						rs.getString("username"),
						rs.getString("user_password"),
						rs.getString("first_name"),
						rs.getString("last_name"));
				users.add(user);
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return users;	
	}
	
	public static boolean AddUser(User user) throws SQLException {
		Connection db = null;
		boolean success = true;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			String command = "insert into users (username, user_password, first_name, last_name) values (?,?,?,?);";
			PreparedStatement insert = db.prepareStatement(command);
			insert.setString(1, user.getUserName());
			insert.setString(2, user.getPassword());
			insert.setString(3, user.getFirstName());
			insert.setString(4, user.getLastName());
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
