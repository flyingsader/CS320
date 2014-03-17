<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reservations</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<strong>Room Reservations for E&T A309</strong>
	<table>
		<tr>
			<th></th>
			<c:forEach items="${weekDays}" var="day">
				<th>${day.day}</th>
			</c:forEach>
		</tr>
		<c:forEach items="${timeDays}" var="time">
			<tr>
				<td>${time.time}</td>
				<c:forEach items="${weekDays}" var="day">
					<td><c:forEach items="${reservations}" var="res">
							<c:if test="${res.day.id == day.id and res.time.id == time.id}">
					<a href="DeleteReservation?id=${res.id}">${res.reservedBy}</a>
				</c:if>
						</c:forEach></td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
	<br />
	<form action="Reservations" method="post">
		<select name="weekDay">
			<c:forEach items="${weekDays}" var="day">
				<option value="${day.id}">${day.day}</option>
			</c:forEach>
		</select>&nbsp;<select name="timeDay">
			<c:forEach items="${timeDays}" var="time">
				<option value="${time.id}">${time.time}</option>
			</c:forEach>
		</select>&nbsp;<input name="reserveName" /> <input type="submit"
			name="reserve" value="Reserve" /> <br /> <br />
		<c:if test="${not empty errorMessage}">
			<p style="color: red">${errorMessage}</p>
		</c:if>
		<p></p>
	</form>
</body>
</html>