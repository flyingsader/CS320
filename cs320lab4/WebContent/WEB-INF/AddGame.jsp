<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Game</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<form action="AddGame" method="post">
		<table>
			<tr><td>Date (MM/dd/yyyy):</td><td><input name="gameDate" /></td></tr>
			<tr><td>Opponent:</td><td><input name="gameOpponent" /></td></tr>
			<tr><td>Location:</td><td><input name="gameLocation" /></td></tr>
			<tr><td colspan="2"><input type="submit" name="Add" value="Add" /></td></tr>
	</table>
	</form>
</body>
</html>