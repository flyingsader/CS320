<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Planning</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<p>${promptText}</p>
	<form action="CoursePlanning" method="post">
		<c:if test="${not empty finish}">
			<c:forEach items="${cp.plan}" var="p">
				<u>${p.quarter}</u>
				<table>
					<tr>
						<th>Code</th>
						<th>Title</th>
						<th>Prerequisites</th>
					</tr>
					<c:forEach items="${p.courses}" var="course">
						<tr>
							<td>${course.code}</td>
							<td>${course.name}</td>
							<td>${course.prerequisitesList}</td>
						</tr>
					</c:forEach>
				</table>
				<br/><br/>
			</c:forEach>
			<br/><br/>
			<a href="CoursePlanner">Done</a>
		</c:if>
		<c:if test="${empty finish}">
			<table>
				<tr>
					<th></th>
					<th>Code</th>
					<th>Title</th>
					<th>Prerequisites</th>
				</tr>
				<c:forEach items="${availableCourses}" var="course">
					<tr>
						<td><input type='checkbox' name='selectedCourses' value="${course.code}" /></td>
						<td>${course.code}</td>
						<td>${course.name}</td>
						<td>${course.prerequisitesList}</td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<br />
			<input type="submit" name="next" value="Next" />
			<c:if test="${availableCourses.size() != courses.size()}">
				<input type="submit" name="finish" value="Finish" />
			</c:if>
		</c:if>
	</form>
</body>
</html>