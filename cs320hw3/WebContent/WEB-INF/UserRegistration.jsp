<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<form action='UserRegistration' method='post'>
		<a href='Login'>${loginText}</a> <br />
		<p style='color: red'>${errorMessage}</p>
		<br />
		<table>
			<tr>
				<td>UserName:</td>
				<td><input name='username' /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name='password' type='password' /></td>
			</tr>
			<tr>
				<td>Retype Password:</td>
				<td><input name='retypePassword' type='password' /></td>
			</tr>
			<tr>
				<td>First Name (optional):</td>
				<td><input name='firstName' /></td>
			</tr>
			<tr>
				<td>Last Name (optional):</td>
				<td><input name='lastName' /></td>
			</tr>
			<tr>
				<td><input type='submit' name='register' value='Register' /></td>
				<td><a href='CoursePlanner'>Cancel</a></td>
			</tr>
		</table>
	</form>
</body>
</html>