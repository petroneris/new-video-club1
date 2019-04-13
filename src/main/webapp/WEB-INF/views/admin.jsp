<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<%@include file="header.jsp"%>
<title>Admin page</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="admin">
				<h1>ADMIN PAGE</h1>				
				<h3>admin: ${user}</h3>
				<br>
			</div>
			<div class="adminlink">
			<br>
				<a href="${pageContext.request.contextPath}/admin/allFilms">Films
					- Info/ Add/ Edit/ Delete</a> <br> <a
					href="${pageContext.request.contextPath}/admin/rentedFilms">Rented films 
					- View/ Return </a> <br> <a
					href="${pageContext.request.contextPath}/admin/rentNewFilm">Rent available films
					</a> <br> <br> <a
					href="${pageContext.request.contextPath}/admin/allUsers">Users
					- Info/ Add/ Edit/ Delete</a> <br> <a
					href="${pageContext.request.contextPath}/admin/usersWithFilms">
					Users with rented films</a> <br> <a
					href="${pageContext.request.contextPath}/admin/usersWithoutFilms">
					Users without films</a> <br> <br> <a
					href="${pageContext.request.contextPath}/admin/statistics">Statistics</a>
				<br> <a
					href="${pageContext.request.contextPath}/admin/allAdmins">Admins
					- Info/ Add/ Edit/ Delete</a><br> 
				<br> <a href="${pageContext.request.contextPath}/logout"
					class="btn btn-primary btn-lg active" role="button">Logout</a>
			</div>
		</div>
	</div>
</body>
</html>
