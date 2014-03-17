package cs320lab8;

public class TimeDay {
	private int _id;
	private String _time;
	
	public TimeDay(int id, String time)
	{
		_id = id;
		_time = time;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public String getTime()
	{
		return _time;
	}
}
