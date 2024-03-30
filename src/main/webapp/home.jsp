<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="com.ToDoApp.model.*" %>
   
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>To Do List</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
		integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
		crossorigin="anonymous" referrerpolicy="no-referrer" />
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=IM+Fell+DW+Pica:ital@0;1&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="css/main.css" />
</head>

<body>
<%
	HttpSession Session = request.getSession();
	User user_temp = (User) Session.getAttribute("user");
	if(user_temp == null)
		response.sendRedirect("Authentication");
%>

	<div class="header">
		<span class="title"><img style="height: 2rem; margin-right: 8px;" src="img/notebook.png" alt="">TO DO APP</span>
		<span class="greeting">Hello, ${user.getFirstName()}</span>
		<a href="/ToDoList-App/Logout" class="logOut">Log Out</a>
	</div>

	<div class="container">
		<header>
			<h1 style="text-align: center;">Let's make your own plan</h1>
			<form id="new-task-form" action="/ToDoList-App/Home" method="post">
				<input type="hidden" name="ACTION" value="ADD">
				<input type="text" name="new-task-input" id="new-task-input" placeholder="What do you have planned?" />
				<input type="submit" id="new-task-submit" value="Add" />
			</form>
		</header>
		<main>
			<section class="task-list">
				<h2>Tasks:</h2>
				<div id="tasks">
				<c:forEach var="i" items="${LIST_TASK}">
					<div class="content">
					
						<div class="wrapper-text">
							<p class="text">${i.task}</p>
						</div>
						
						<a href="/ToDoList-App/Home?ACTION=DELETE&id=${i.id}" class="delete"><i class="fa fa-trash" aria-hidden="true"></i></a>
					</div>
					</c:forEach>
				</div>
			</section>
		</main>
	</div>

	
</body>

</html>