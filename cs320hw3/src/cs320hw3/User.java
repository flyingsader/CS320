package cs320hw3;

public class User {
	
	private String _userName;
	private String _password;
	private String _firstName;
	private String _lastName;
	
	public User(String username, String password, String firstName, String lastName)
	{
		_userName = username;
		_password = password;
		_firstName = firstName;
		_lastName = lastName;
	}
	
	public String getUserName()
	{
		return _userName;
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
