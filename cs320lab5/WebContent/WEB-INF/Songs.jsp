<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Songs</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<h1>Billboard Top Songs</h1>
	<form action="Songs" method="post">
		<table>
			<tr>
				<th>Ranking</th>
				<th>Name</th>
				<th>Artist</th>
				<th>Operations</th>
			</tr>
			<c:forEach items="${songs}" var="song">
				<tr>
					<td text-align="center">${song.ranking}</td>
					<td>${song.name}</td>
					<td>${song.artist}</td>
					<td><a href="Up?ranking=${song.ranking}">Up</a>|<a
						href="Down?ranking=${song.ranking}">Down</a></td>
				</tr>
			</c:forEach>
			<tr>
			<td> </td>
			<td><input name="songName" /></td>
			<td><input name="songArtist" /></td>
			<td><input type="submit" name="Add" value="Add" /></td>
			</tr>
		</table>
	</form>
</body>
</html>