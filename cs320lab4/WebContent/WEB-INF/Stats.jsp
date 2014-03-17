<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Stats</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<form action="Stats" method="post">
		<p>
			<strong>Lakers vs. ${game.opponent} in ${game.location} on
				${game.date}</strong> <input type="hidden" name="id" value="${game.id}" />
		</p>
		<table>
			<tr>
				<th>Player</th>
				<th>Points</th>
				<th>Rebounds</th>
				<th>Assists</th>
			</tr>
			<c:forEach items="${game.stats}" var="stat">
				<tr>
					<td>${stat.player}</td>
					<td>${stat.points}</td>
					<td>${stat.rebounds}</td>
					<td>${stat.assists}</td>
				</tr>
			</c:forEach>
			<tr>
				<td><select name="player">
						<c:forEach items="${players}" var="player">
							<option value="${player}">${player}</option>
						</c:forEach>
				</select></td>
				<td><input name="statPoints" /></td>
				<td><input name="statRebounds" /></td>
				<td><input name="statAssists" /></td>
			</tr>
			<tr>
				<td colspan="4"><input type="submit" name="Add" value="Add" /></td>
			</tr>
		</table>
		<br /> <br /> <a href="Games">Back to Games</a>
	</form>
</body>
</html>