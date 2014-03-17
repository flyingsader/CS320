package cs320hw4;

public class User {
	
	private int _id;
	private String _userName;
	private String _password;
	private String _firstName;
	private String _lastName;
	
	private static int _idCounter = 1;
	
	public User(int id, String username, String password, String firstName, String lastName)
	{
		_id = id;
		_userName = username;
		_password = password;
		_firstName = firstName;
		_lastName = lastName;
	}
	
	public User(String username, String password, String firstName, String lastName)
	{
		_id = _idCounter++;
		_userName = username;
		_password = password;
		_firstName = firstName;
		_lastName = lastName;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public String getUserName()
	{
		return _userName;
	}
	
	public String getPassword()
	{
		return _password;
	}
	
	public String getFirstName()
	{
		return _firstName;
	}
	
	public String getLastName()
	{
		return _lastName;
	}
	
	public boolean isPassword(String password)
	{
		return _password.compareTo(password) == 0;
	}

}
