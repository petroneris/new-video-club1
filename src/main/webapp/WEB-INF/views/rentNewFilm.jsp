<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<title>Rent new film</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<p class="logUser">
				<span class="glyphicon glyphicon-user lb"></span> <strong>${usernameAdm}</strong>
			</p>
			<b></b>
			<h3 class="bg-info text-info">RENT FILM - Select user</h3>
			<br>
			<table class="table table-bordered table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th></th>
					</tr>
				</thead>
				<c:forEach var="user" items="${usersList2}">
					<tbody>
						<tr id="${user.username}">
							<td>${user.id}</td>
							<td>${user.username}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td><a
								href="${pageContext.request.contextPath}/admin/availableFilms/${user.username}"
								class="btn btn-primary active" role="button">Select</a></td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
			<br>
			<div class="centrlink">
				<a href="${pageContext.request.contextPath}/admin">Back to admin
					page</a>
			</div>
		</div>
		<br> <br> <br>
	</div>
</body>
</html>