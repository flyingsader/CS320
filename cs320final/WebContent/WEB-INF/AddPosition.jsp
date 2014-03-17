<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Post New Position</title>
</head>
<body>
<strong><a href="JobApplications">CS Jobs</a> - Job Positions</strong>
<ul>
<c:forEach items="${jobPositions}" var="job">
	<li>${job.jobTitle}</li>
</c:forEach>
</ul>
<form action="AddPosition" method="post">
<p>New Position: <input name="position" type="text" /><input name="add" value="Add" type="submit" /></p>
</form>
</body>
</html>