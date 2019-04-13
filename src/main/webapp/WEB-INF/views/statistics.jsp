<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<title>Statistics</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<p class="logUser">
				<span class="glyphicon glyphicon-user lb"></span> <strong>${usernameAdm}</strong>
			</p>
			<b></b>
			<div class="stat">
				<h1 class="title3">STATISTICS</h1>
				<br>
				<h3 class="title3">FILMS</h3>
				<br>
				<table class="table table-bordered table-hover">
					<tr>
						<th>Id</th>
						<th>Title</th>
						<th>Genre</th>
						<th>Year</th>
						<th>Total rents</th>
					</tr>
					<c:forEach var="flm" items="${filmsList}">
						<tr>
							<td>${flm.id}</td>
							<td>${flm.title}</td>
							<td>${flm.genre}</td>
							<td>${flm.year}</td>
							<td>${flm.nRentperfilm}</td>
						</tr>
					</c:forEach>
				</table>
				<h4 class="text-info bg-info">Total films: ${nrFilms}</h4>
				<h4 class="text-info bg-info">
					Average rents per film :
					<fmt:formatNumber type="number" groupingUsed="false"
						value="${statNrRentPerFilm}" />
				</h4>
				<br>
				<h3 class="title3">USERS</h3>
				<br>
				<table class="table table-bordered table-hover">
					<tr>
						<th>Id</th>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Total films rented</th>
					</tr>
					<c:forEach var="user" items="${usersList}">
						<tr>
							<td>${user.id}</td>
							<td>${user.username}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.nRentperuser}</td>
						</tr>
					</c:forEach>
				</table>
				<h4 class="text-info bg-info">Total users: ${nrUsers}</h4>
				<h4 class="text-info bg-info">
					Average rents per user:
					<fmt:formatNumber type="number" groupingUsed="false"
						value="${statNrFilmPerUser}" />
				</h4>
				<br>
			</div>
			<div class="centrlink">
				<a href="${pageContext.request.contextPath}/admin">Back to admin
					page</a>
			</div>
			<br> <br> <br>
		</div>
	</div>
</body>
</html>