<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<%@include file="header.jsp"%>
<style>
body {
	background-image: url("static/images/springBloss4ab.png");
}
</style>
<title>WELCOME!</title>
</head>
<body>
	<div class="container">
		<div class="backgrd">
			<br>
			<h1>WELCOME!</h1>
			<br>
			<h1>NEW VIDEO CLUB SPRING</h1>
			<br> <br>
			<div class="indexLog">
				<a href="${pageContext.request.contextPath}/login">Login</a>
			</div>
		</div>
	</div>
</body>
</html>