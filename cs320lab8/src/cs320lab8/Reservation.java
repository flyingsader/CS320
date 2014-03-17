package cs320lab8;

public class Reservation {
	private int _id;
	private WeekDay _day;
	private TimeDay _time;
	private String _reservedBy;
	
	public Reservation(int id, WeekDay day, TimeDay time, String reservedBy)
	{
		_id = id;
		_day = day;
		_time = time;
		_reservedBy = reservedBy;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public WeekDay getDay()
	{
		return _day;
	}
	
	public TimeDay getTime()
	{
		return _time;
	}
	
	public String getReservedBy()
	{
		return _reservedBy;
	}
}
