package cs320lab8;

public class WeekDay {
	private int _id;
	private String _dayName;
	
	public WeekDay(int id, String name)
	{
		_id = id;
		_dayName = name;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public String getDay()
	{
		return _dayName;
	}

}
