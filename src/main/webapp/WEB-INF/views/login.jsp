<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<title>Login page</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<div class="logform">
				<c:url var="loginUrl" value="/login" />
				<form action="${loginUrl}" method="post" class="form-horizontal">
					<div class="form-group">
						<div id="username_container">
							<span id="glyp_us" class="glyphicon glyphicon-user"></span> <input
								type="text" id="inputus" name="username"
								placeholder="Enter Username" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<div id="password_container">
							<span id="glyp_pass" class="glyphicon glyphicon-lock"></span> <input
								type="password" id="inputpass" name="password"
								placeholder="Enter Password" class="form-control">
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <br> <br>
					<div class="indexLog">
						<input type="submit" class="btn btn-primary btn-lg" value="Log in">
					</div>
					<br> <br>
					<c:if test="${param.error != null}">
						<div class="prm alert alert-danger">
							<p class="param">Invalid username and/or password.</p>
						</div>
					</c:if>
					<c:if test="${param.logout != null}">
						<div class="prm alert alert-success">
							<p class="param">You have been logged out successfully.</p>
						</div>
					</c:if>
					<c:if test="${param.timeout == true}">
						<div class="prm alert alert-warning">
							<p class="param">Session has timed out...</p>
						</div>
					</c:if>
				</form>
			</div>
			<div class="centrlink">
				<a href="${pageContext.request.contextPath}/">Return to the
					beginning of application</a>
			</div>
		</div>
	</div>
</body>
</html>