<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%-- set data source --%>
<sql:setDataSource
    driver="com.mysql.jdbc.Driver"
    url="jdbc:mysql://localhost/cs320lab7"
    user="root"
    password="Smalls0622!"/>

<c:if test="${empty param.id}">
	<c:redirect url="GuestBook.jsp" />
</c:if>
<c:if test="${not empty param.id}">
	<sql:query var="entry">
		select * from guestbook where id = ?
		<sql:param value="${param.id}"/>
	</sql:query>
</c:if>
<c:if test="${not empty param.edit}">
	<sql:update>
    update guestbook (name, message) values (?, ?) where id = ?
    <sql:param value="${param.name}" />
		<sql:param value="${param.message}" />
		<sql:param value="${param.id}" />
	</sql:update>
	<c:redirect url="GuestBook.jsp" />
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Comment</title>
</head>
<body>
	<form action="EditComment.jsp" method="post">
		<input type="hidden" name="id" value="${entry.id}"/>
		Name: <input type="text" name="name" value="${entry.name}"/> <br /> Message: <input
			type="text" name="message" value="${entry.message}" /> <br /> <input type="submit"
			name="edit" value="Edit" />
	</form>
</body>
</html>