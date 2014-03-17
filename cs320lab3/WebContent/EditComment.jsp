<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="gb" class="cs320lab3.GuestBookBean" scope="application" />

<c:if test="${not empty param.save}">
	<%-- set name & message into the new entry --%>
	<c:set target="${gb.editEntry}" property="name" value="${param.name}" />
	<c:set target="${gb.editEntry}" property="message"
		value="${param.message}" />
	<c:redirect url="GuestBook.jsp" />
</c:if>

<c:if test="${not empty param.i}">
	<%-- invoke setEditEntry() to create an empty entry --%>
	<jsp:setProperty name="gb" property="editIndex" value="${param.i}" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Edit Comment</title>
</head>
<body>
	<form action="EditComment.jsp" method="post">
		Name: <input type="text" name="name" value="${gb.editEntry.name}" /> <br /> Message: <input
			type="text" name="message" value="${gb.editEntry.message}" /> <br />
		<input type="submit" name="save" value="Save" />
	</form>
</body>
</html>
