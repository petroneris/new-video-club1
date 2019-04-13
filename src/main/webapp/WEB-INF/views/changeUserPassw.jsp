<!DOCTYPE html>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.12.0/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.12.0/additional-methods.js"></script>
<title>Change Password</title>
</head>
<body>
	<div class="wrapper">
		<div class="container">
			<p class="logUser">
				<span class="glyphicon glyphicon-user lb"></span> <strong>${usernamep}</strong>
			</p>
			<b></b>			
			<div class="logformCP">
				<form
					action="${pageContext.request.contextPath}/home/changeUserPassw"
					method="post" id="chpass_form" class="form-horizontal">
					<div class="form-group">
						<label class="col-md-2 control-label">Old Password</label>
						<div class="col-md-10 inputGroupContainer">
							<div class="input-group input-group-lg">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input name="oldpassword"
									id="oldpassword" placeholder="Old Password"
									class="form-control" type="password" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">New Password</label>
						<div class="col-md-10 inputGroupContainer">
							<div class="input-group input-group-lg">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock bl"></i></span> <input
									name="newpassword" id="newpassword"
									placeholder="New Password" class="form-control"
									type="password" required>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">Confirm Password</label>
						<div class="col-md-10 inputGroupContainer">
							<div class="input-group input-group-lg">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock gr"></i></span> <input
									name="confirmpassword" id="confirmpassword"
									placeholder="Confirm New Password" class="form-control"
									type="password" required>
							</div>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <br> <br>
					<div class="indexLog">
						<input type="submit" class="btn btn-primary btn-lg" value="Save">
					</div>
					<br> <br>
					<c:if test="${param.err != null}">
						<div class="prm alert alert-danger">
							<p class="param">Invalid old password.</p>
						</div>
					</c:if>
					<c:if test="${param.succ != null}">
						<div class="prm alert alert-success">
							<p class="param">You have changed password successfully.</p>
						</div>
					</c:if>
				</form>
			</div>
			<div class="centrlink">
				<a href="${pageContext.request.contextPath}/home">Back to home
					page </a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	//validation function for input passwords	
		$(document).ready(function() {

			$("#chpass_form").validate({

				errorClass : "my-error-class",
				rules : {
					oldpassword : {
						required : true,
						minlength : 4
					},
					newpassword : {
						required : true,
						minlength : 4
					},
					confirmpassword : {
						required : true,
						minlength : 4,
						equalTo : "#newpassword"
					}
				},
				messages : {
					oldpassword : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 4 characters"
					},
					newpassword : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 4 characters"
					},
					confirmpassword : {
						required : "Please confirm new password",
						minlength : "Your data must be at least 4 characters"
					}
				},
				errorPlacement : function(error, element) {
					error.appendTo(element.parent().parent().after());
				},
			});
		});
	</script>

</body>
</html>