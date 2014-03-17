<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Applications</title>
<style>
table,th,td {
	border: 1px solid black;
	border-collapse: collapse;
	padding: 5px
}
</style>
</head>
<body>
	<strong>CS Jobs</strong>
	<p>
		<a href="AddPosition">Post a Position</a> | <a href="Apply">Apply
			For Position(s)</a>
	</p>
	<table>
		<tr>
			<th><a href="JobApplications?sort=job_title">Position</a></th>
			<th><a href="JobApplications?sort=job_applicant">Applicant</a></th>
			<th><a href="JobApplications?sort=time_applied">Submitted On</a></th>
		</tr>
		<c:forEach items="${jobApplications}" var="jobApp">
			<tr>
				<td>${jobApp.jobTitle}</td>
				<td>${jobApp.jobApplicant}</td>
				<td>${jobApp.timeApplied}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>