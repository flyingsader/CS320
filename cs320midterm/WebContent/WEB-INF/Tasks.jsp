<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tasks</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}

tr.past td {
	background-color: red
}

tr.coming td {
	background-color: yellow
}
</style>
</head>
<body>
	<form action="Tasks" method="post">
		<p>
			Current Tasks | <a href="Completed">Completed Tasks</a>
		</p>
		<table>
			<tr>
				<th>Message</th>
				<th>Due Date</th>
				<th>Operations</th>
			</tr>
			<c:forEach items="${currentTasks}" var="task">
				<c:if test="${task.pastDue}">
					<tr class="past">
				</c:if>
				<!-- 
				<c:if test="${task.comingUp}">
					<tr class="coming">
				</c:if>  -->
				<c:if test="${!task.pastDue && !task.comingUp}">
					<tr>
				</c:if>
				<td>${task.message}</td>
				<td style="text-align: center">${task.dueDate}</td>
				<td style="text-align: center"><c:if test="${!task.pastDue}">
						<a href="CompleteTask?id=${task.id}">Completed</a> |
					</c:if> <a href="RemoveTask?id=${task.id}">Remove</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td><input name="taskMessage" /></td>
				<td><input name="taskDueDate" /></td>
				<td style="text-align: center"><input type="submit" name="add"
					value="Add" /></td>
			</tr>
		</table>
	</form>
</body>
</html>