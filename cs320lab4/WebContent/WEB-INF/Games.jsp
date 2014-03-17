<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Games</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<table>
		<tr>
			<th>Date</th>
			<th>Opponent</th>
			<th>Location</th>
			<th></th>
		</tr>

		<c:forEach items="${games}" var="game">
			<tr>
				<td>${game.date}</td>
				<td>${game.opponent}</td>
				<td>${game.location}</td>
				<td><a href="Stats?id=${game.id}">Stats</a></td>
			</tr>
		</c:forEach>

	</table>
	
	<br/><br/>
	<a href="AddGame">Add Game</a>
</body>
</html>