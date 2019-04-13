<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<title>Rented films per user</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
		<p class="logUser">
				<span class="glyphicon glyphicon-user lb"></span> <strong>${username}</strong>
			</p>
			<b></b>
			<h4 class="title3">RENTED FILMS</h4>
			<br>
			<div class="usertable1">
				<table class="table table-bordered table-hover">
					<tr>
						<th>Title</th>
						<th>Genre</th>
						<th>Year</th>
					</tr>
					<c:forEach var="flm" items="${rentedFilmsPerUser}">
						<tr>
							<td>${flm.title}</td>
							<td>${flm.genre}</td>
							<td>${flm.year}</td>
						</tr>
					</c:forEach>
				</table>
				<h4 class="text-info bg-info">Total films:
					${nrFlmUser}</h4>
			</div>
			<br>
			<div class="centrlink">
				<a href="${pageContext.request.contextPath}/home">Back to home page</a>
			</div>
		</div>
	</div>
</body>
</html>