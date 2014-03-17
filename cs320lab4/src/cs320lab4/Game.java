package cs320lab4;

import java.util.ArrayList;
import java.util.List;

public class Game {
	private int _id;
	private String _date;
	private String _opponent;
	private String _location;
	private List<Stat> _stats;
	
	private static int idCounter = 1;
	
	public Game()
	{
		_id = idCounter++;
		_stats = new ArrayList<Stat>();
	}
	
	public Game(String date, String opponent, String location)
	{
		this();
		_date = date;
		_opponent = opponent;
		_location = location;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public String getDate()
	{
		return _date;
	}
	
	public void setDate(String date)
	{
		_date = date;
	}
	
	public String getOpponent()
	{
		return _opponent;
	}
	
	public void setOpponent(String opponent)
	{
		_opponent = opponent;
	}
	
	public String getLocation()
	{
		return _location;
	}
	
	public void setLocation(String location)
	{
		_location = location;
	}
	
	public List<Stat> getStats()
	{
		return _stats;
	}
	
	public void setStats(List<Stat> stats)
	{
		_stats = stats;
	}
}
