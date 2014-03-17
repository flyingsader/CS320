package cs320midterm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Task {
	private int _id;
	private String _message;
	private String _dueDate;
	private String _completionDate;

	private static int _idCounter = 1;

	public Task(String message, String dueDate) {
		this(message, dueDate, "");
	}

	public Task(String message, String dueDate, String completionDate) {
		_id = _idCounter++;
		_message = message;
		_dueDate = dueDate;
		_completionDate = completionDate;
	}

	public int getId() {
		return _id;
	}

	public String getMessage() {
		return _message;
	}

	public String getDueDate() {
		return _dueDate;
	}

	public String getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(String completionDate) {
		_completionDate = completionDate;
	}

	public boolean isPastDue() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date pd = format.parse(_dueDate);
			Calendar pDue = Calendar.getInstance();
			pDue.setTime(pd);
			pDue.add(Calendar.DAY_OF_YEAR, 1);

			return pDue.before(Calendar.getInstance());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean isComingUp() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date pd = format.parse(_dueDate);
			Calendar pDue = Calendar.getInstance();
			Calendar today = pDue;
			Calendar tomorrow = today;
			tomorrow.add(Calendar.DAY_OF_YEAR, -1);
			pDue.setTime(pd);
			// pDue.add(Calendar.DAY_OF_YEAR, 1);

			return format.format(pDue.getTime()).equals(
					format.format(today.getTime()))
					|| format.format(pDue.getTime()).equals(
							format.format(tomorrow.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
}
