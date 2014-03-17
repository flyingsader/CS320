package cs320hw4;

import java.util.ArrayList;
import java.util.List;

public class QuarterPlan {
	
	private String _quarter;
	private List<Course> _courses;
	
	public QuarterPlan(String quarter)
	{
		_courses = new ArrayList<Course>();
		_quarter = quarter;
	}
	
	public String getQuarter()
	{
		return _quarter;
	}
	
	public List<Course> getCourses()
	{
		return _courses;
	}
	
	public void setCourses(List<Course> courses)
	{
		_courses = courses;
	}

}
