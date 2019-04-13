<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<title>Users with films</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<p class="logUser">
				<span class="glyphicon glyphicon-user lb"></span> <strong>${usernameAdm}</strong>
			</p>
			<b></b>
			<h3 class="title3">USERS WITH RENTED FILMS</h3>
			<br>
			<table class="table table-bordered table-hover">
				<tr>
					<th>Id</th>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th colspan="5">Title</th>
				</tr>
				<c:forEach var="user" items="${usersWithFilms}">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<c:forEach var="flm" items="${user.films}">
							<td>${flm.title}</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
			<br>
			<div class="centrlink">
				<a href="${pageContext.request.contextPath}/admin">Back to admin
					page</a>
			</div>
			<br> <br> <br>
		</div>
	</div>
</body>
</html>