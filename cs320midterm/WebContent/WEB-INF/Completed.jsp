<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Completed Tasks</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<p>
		<a href="Tasks">Current Tasks</a> | Completed Tasks
	</p>
	<table>
		<tr>
			<th>Message</th>
			<th>Due Date</th>
			<th>Completion Date</th>
		</tr>
		<c:forEach items="${completedTasks}" var="task">
			<tr>
				<td>${task.message}</td>
				<td style="text-align: center">${task.dueDate}</td>
				<td style="text-align: center">${task.completionDate}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>