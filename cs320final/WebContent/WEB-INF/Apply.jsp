<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Application</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}

table td.positions {
	text-align: top;
}

table td.apply {
	text-align: right;
}
</style>
</head>
<body>
	<strong><a href="JobApplications">CS Jobs</a> - Application</strong><br/><br/>
	<form action="Apply" method="post">
		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="applicant" /></td>
			</tr>
			<tr>
				<td class="positions">Positions:</td>
				<td><c:forEach items="${jobPositions}" var="pos">
						<input type="checkbox" name="positions" value="${pos.jobTitle}" />${pos.jobTitle}<br/>
					</c:forEach></td>
			</tr>
			<tr>
				<td colspan="2" class="apply"><input type="submit" name="apply"
					value="Apply" /></td>
			</tr>
		</table>
	</form>
</body>
</html>