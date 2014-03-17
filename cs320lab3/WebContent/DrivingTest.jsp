<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="dt" class="cs320lab3.DrivingTestBean" scope="session" />

<c:if test="${empty param.finish}">
	<jsp:setProperty name="dt" property="currentQuestionIndex"
		value="${dt.currentQuestionIndex + 1}" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Driving Test</title>
</head>
<body>
	<form action="DrivingTest.jsp" method="post">
		<c:if test="${not empty param.finish}">
			<p>
				Your score is:
				<jsp:getProperty name="dt" property="score" /></p>
		</c:if>
		<c:if test="${empty param.finish}">
			<c:set target="${param.description}"
				value="${dt.getCurrentQuestion().getDescription()}" />
			<c:set target="${param.answerA}"
				value="${dt.getCurrentQuestion().getAnswerA()}" />
			<c:set target="${param.answerB}"
				value="${dt.getCurrentQuestion().getAnswerB()}" />
			<c:set target="${param.answerC}"
				value="${dt.getCurrentQuestion().getAnswerC()}" />
			<c:out value="${param.description}" />
			<ol>
				<li><c:out value="${param.answerA}" /></li>
				<li><c:out value="${param.answerB}" /></li>
				<li><c:out value="${param.answerC}" /></li>
			</ol>
			<c:if test="${dt.lastQuestion}">
				<input type="submit" name="finish" value="Finish" />
			</c:if>
			<c:if test="${not dt.lastQuestion}">
				<input type="submit" name="next" value="Next" />
			</c:if>
		</c:if>
	</form>
</body>
</html>
