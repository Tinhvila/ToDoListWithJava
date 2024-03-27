<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="com.ToDoApp.model.*" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>To Do List</title>
</head>
<body>

<%
	HttpSession session1 = request.getSession(); 
	User user = (User)session.getAttribute("user");
	if(user == null)
		response.sendRedirect(request.getContextPath() +  "/Authentication");
%>
	<h1>This is a home page</h1>
		<table>
			<tr>
				<th>ID</th>
				<th>FIRST NAME</th>
				<th>EMAIL</th>
				<th>PASSWORD</th>
			</tr>
	<c:forEach var="i" items="${USER_LIST }">
		<tr>
			<td>${i.userId}</td>
			<td>${i.firstName}</td>
			<td>${i.email}</td>
			<td>${i.passWord}</td>
		</tr>
	</c:forEach>
		</table>
		
		<a href="<%=request.getContextPath() %>/Logout">Logout</a>
	
</body>
</html>