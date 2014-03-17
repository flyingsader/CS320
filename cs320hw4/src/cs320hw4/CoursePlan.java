package cs320hw4;

import java.util.ArrayList;
import java.util.List;

public class CoursePlan {

	private List<QuarterPlan> _plan;
	private int _currentQuarter;
	private int _currentYear;

	public CoursePlan() {
		_plan = new ArrayList<QuarterPlan>();
		_currentQuarter = 1;
		_currentYear = 2014;
	}

	public List<QuarterPlan> getPlan() {
		return _plan;
	}
	
	public int getCurrentQuarter()
	{
		return _currentQuarter;
	}
	
	public String getCurrentQuarterString()
	{
		String q = "";
		switch(_currentQuarter)
		{
		case 1:
			q = "Winter";
			break;
			
		case 2:
			q = "Spring";
			break;
			
		case 3:
			q = "Summer";
			break;
			
		case 4:
			q = "Fall";
			break;
		}
		
		return String.format("%s %d", q, _currentYear);
	}
	
	public void nextQuarter()
	{
		if(_currentQuarter != 4)
			_currentQuarter++;
		else
		{
			_currentQuarter = 1;
			_currentYear++;
		}
	}
}
