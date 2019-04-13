<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<%@include file="header.jsp"%>
<title>userHome Page</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="user1">
				<h1>WELCOME!</h1>
				<h3>Home Page</h3>
				<h3>user: ${user}</h3>
				<br>
			</div>
			<div class="userlink">
			<br>
				<a href="${pageContext.request.contextPath}/home/rentedFilmsPerUser">
					Rented Films</a> <br> <a
					href="${pageContext.request.contextPath}/home/userRentNewFilm">Rent
					new film</a><br>
				<a href="${pageContext.request.contextPath}/home/changeUserPassw">Change
					password</a><br> <br>					
				<br> <a href="${pageContext.request.contextPath}/logout"
					class="btn btn-primary btn-lg active" role="button">Logout</a>
			</div>
		</div>
	</div>
</body>
</html>
