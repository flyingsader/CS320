<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Course</title>
</head>
<body>
	<form action='EditCourse' method='post'>
		<input type='hidden' name='id' value='${id}' /><a href='Login'>${loginText}</a><br />
		<table>
			<tr>
				<td>Code:</td>
				<td><input name='code' value="${course.code}" /></td>
			</tr>
			<tr>
				<td>Title:</td>
				<td><input name='title' value="${course.name}" /></td>
			</tr>
			<tr>
				<td style='vertical-align: top'>Prerequisites:
					${course.prerequisitesList}</td>
				<td><ul>
						<c:forEach items="${courses}" var="req">
							<c:set var="isReq"
								value="${fn:contains(course.prerequisitesList, req.code)}"
								scope="session" />
							<c:if test="${req.id != id}">
								<li>${req.code}<input name="prereq" type="checkbox"
									value="${req.id}" <c:if test="${sessionScope.isReq}">
									checked="true"
									</c:if> /></li>
							</c:if>
						</c:forEach>
					</ul></td>
			</tr>
			<tr>
				<td colspan='2'><input type='submit' name='edit' value='Save' />
		</table>
	</form>
</body>
</html>