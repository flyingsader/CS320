<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add Course</title>
</head>
<body>
	<form action='AddCourse' method='post'>
		<a href='Login'>${loginText}</a><br />
		<table>
			<tr>
				<td>Code:</td>
				<td><input name='code' /></td>
			</tr>
			<tr>
				<td>Title:</td>
				<td><input name='title' /></td>
			</tr>
			<tr>
				<td style='vertical-align: top'>Prerequisites:</td>
				<td><ul>

						<c:forEach items="${courses}" var="course">
							<li>${course.code}<input name='prereq' type='checkbox' value='${courses.indexOf(course)}' /></li>
						</c:forEach>
					</ul></td>
			</tr>
			<tr>
				<td colspan='2'><input type='submit' name='add' value='Add' />
		</table>
	</form>
</body>
</html>