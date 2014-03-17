<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course Planner</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<a href='Login'>${loginText}</a>
	<br />
	<table>
		<tr>
			<th>Code</th>
			<th>Title</th>
			<th>Prerequisites</th>
			<th>Operation</th>
		</tr>

		<c:forEach items="${courses}" var="course">
			<tr>
				<td>${course.code}</td>
				<td>${course.name}</td>
				<td>${course.prerequisitesList}</td>
				<td><a href='EditCourse?id=${course.id}'>Edit</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<a href='AddCourse'>Add Course</a> | <a href='CoursePlanning'>Course Planner</a>
</body>
</html>