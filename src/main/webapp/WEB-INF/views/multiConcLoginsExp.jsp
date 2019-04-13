<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<%@include file="header.jsp"%>
<title>Multiple concurrent logins page</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="accden">
				<h1 class="text-danger bg-danger">This session has been expired!</h1>
				<br>
				<h2 class="text-danger bg-danger">(possibly due to multiple concurrent logins being attempted as the same user)</h2>
				<br> <br>
			</div>
			<div class="centrlink">
				<a href="${pageContext.request.contextPath}/login">Go to Login</a>
			</div>
		</div>
	</div>
</body>
</html>