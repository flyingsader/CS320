package cs320.hw1;

import java.util.List;

public class Course {
	private int _id;
	private String _code;
	private String _name;
	private List<Course> _prereqs;

	private static int _idCounter = 1;

	public Course(String code, String name, List<Course> prereqs) {
		_id = _idCounter++;
		_code = code;
		_name = name;
		_prereqs = prereqs;
	}

	public int getId() {
		return _id;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public List<Course> getPrerequisites() {
		return _prereqs;
	}

	public String getPrerequisitesList() {
		StringBuilder sb = new StringBuilder();

		for (Course prereq : _prereqs) {
			sb.append(prereq.getCode() + " ");
		}

		return sb.toString();
	}

	public void setPrereqs(List<Course> prereqs) {
		_prereqs = prereqs;
	}

	public static void sort(List<Course> courses) {
		// Default sort by code
		for (int i = 0; i < courses.size(); i++) {
			int lowest = i;
			for (int j = i + 1; j < courses.size() - 1; j++) {
				if (courses.get(lowest).getCode()
						.compareTo(courses.get(j).getCode()) > 0)
					lowest = j;
			}

			Course a = courses.get(i);
			Course b = courses.get(lowest);
			
		}
	}
}
