package cs320final;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	private static String _url = "jdbc:mysql://cs3.calstatela.edu/cs320stu10";
	private static String _username = "cs320stu10";
	private static String _password = "VYCf*dtA";

	public static List<JobApplication> GetAllJobApplications(String sortColumn)
			throws SQLException {
		List<JobApplication> applications = new ArrayList<JobApplication>();
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from job_applications order by "
					+ sortColumn;

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				JobApplication ja = new JobApplication(
						rs.getString("job_title"),
						rs.getString("job_applicant"),
						rs.getString("time_applied"));
				applications.add(ja);
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return applications;
	}

	public static List<Job> GetAllJobPositions()
			throws SQLException {
		List<Job> jobs = new ArrayList<Job>();
		Connection db = null;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			Statement select = db.createStatement();
			String command = "select * from jobs";

			ResultSet rs = select.executeQuery(command);

			while (rs.next()) {
				Job job = new Job(rs.getInt("id"), rs.getString("title"));
				jobs.add(job);
			}
		} catch (Exception ex) {

		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}

		return jobs;
	}

	public static boolean AddJobApplication(JobApplication jobApp) throws SQLException {
		Connection db = null;
		boolean success = true;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			String command = "insert into job_applications (job_title, job_applicant, time_applied) values (?, ?, ?);";
			PreparedStatement insert = db.prepareStatement(command);
			insert.setString(1, jobApp.getJobTitle());
			insert.setString(2, jobApp.getJobApplicant());
			insert.setString(3, jobApp.getTimeApplied());
			insert.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			success = false;
		} finally {
			if (db != null && !db.isClosed())
				db.close();
		}
		return success;
	}
	
	public static boolean AddJobPosition(Job job) throws SQLException {
		Connection db = null;
		boolean success = true;

		try {
			db = DriverManager.getConnection(_url, _username, _password);
			String command = "insert into jobs (title) values (?);";
			PreparedStatement insert = db.prepareStatement(command);
			insert.setString(1, job.getJobTitle());
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
