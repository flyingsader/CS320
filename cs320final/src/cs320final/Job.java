package cs320final;

public class Job {
	private int _id;
	private String _jobTitle;
	
	private static int _idCounter = 1;
	
	public Job(String jobTitle)
	{
		_id = _idCounter++;
		_jobTitle = jobTitle;
	}
	
	public Job(int id, String jobTitle)
	{
		_id = id;
		_jobTitle = jobTitle;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public String getJobTitle()
	{
		return _jobTitle;
	}
}
