package cs320final;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobApplication {
	private String _jobTitle;
	private String _jobApplicant;
	private String _timeApplied;
	
	private final SimpleDateFormat _dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	
	public JobApplication(String jobTitle, String jobApplicant)
	{
		_jobTitle = jobTitle;
		_jobApplicant = jobApplicant;
		_timeApplied = _dateFormat.format(new Date());
	}
	
	public JobApplication(String jobTitle, String jobApplicant, String timeApplied)
	{
		_jobTitle = jobTitle;
		_jobApplicant = jobApplicant;
		_timeApplied = timeApplied;
		
		if(_timeApplied.endsWith(".0"))
		{
			_timeApplied = _timeApplied.substring(0, _timeApplied.indexOf(":00.0"));
		}
	}
	
	public String getJobTitle()
	{
		return _jobTitle;
	}
	
	public String getJobApplicant()
	{
		return _jobApplicant;
	}
	
	public String getTimeApplied()
	{
		return _timeApplied;
	}
}
