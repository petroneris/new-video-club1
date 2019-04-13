<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@include file="header.jsp"%>
<title>AccessDenied page</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="accden">
				<h1 class="text-danger bg-danger">You are not authorized to
					access this page!</h1>
				<br>
				<h2 class="text-danger bg-danger">Access attempt: ${user}</h2>
				<br> <br>
			</div>
			<div class="centrlink">
				<a href="<c:url value="/logout" />"
					class="btn btn-primary btn-lg active" role="button">Logout</a>
			</div>
		</div>
	</div>
</body>
</html>

