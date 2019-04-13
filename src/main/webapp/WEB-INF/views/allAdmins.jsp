<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<spring:url value="/static/js/allAdmins.js" var="allAdmins" />
<script src="${allAdmins}"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.12.0/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.12.0/additional-methods.js"></script>
<title>Admins</title>
</head>
<body onload="load();">
	<div class="wrapper">		
		<div class="container">					
			<p class="logUser"><span class="glyphicon glyphicon-user lb"></span> <strong>${usernameAdm}</strong></p>  		
		<b></b>
			<h3 class="title3">ADMINS</h3>
			<div class="addButt">
				<button class="btn btn-success" data-toggle="modal"
					data-target="#adda_modal">Add new Admin</button>
			</div>
			<!-- Bootstrap add modal for new Admin-->
			<div class="modal fade" id="adda_modal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel1">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel1">Add new Admin</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" action="javascript:submitt()"
								id="adda_form">

								<div class="form-group">
									<label class="col-md-2 control-label">Username</label>
									<div class="col-md-10 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user"></i></span><input
												name="adda_username" id="adda_username"
												placeholder="Username" class="form-control" type="text">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">Password</label>
									<div class="col-md-10 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-lock"></i></span> <input
												name="adda_password" id="adda_password"
												placeholder="Password" class="form-control" type="password">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">First Name</label>
									<div class="col-md-10  inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user bl"></i></span> <input
												name="adda_firstName" id="adda_firstName"
												placeholder="First Name" class="form-control" type="text">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">Last Name</label>
									<div class="col-md-10 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-user gr"></i></span> <input
												name="adda_lastName" id="adda_lastName"
												placeholder="Last Name" class="form-control" type="text">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">Email</label>
									<div class="col-md-10  inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-envelope"></i></span> <input
												name="adda_email" id="adda_email" placeholder="Email"
												class="form-control" type="text">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">Telephone</label>
									<div class="col-md-10  inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-earphone"></i></span> <input
												name="adda_telephone" id="adda_telephone"
												placeholder="Telephone" class="form-control" type="number">
										</div>
									</div>
								</div>
								<input type="hidden" name="nname" id="role_admin" value="ADMIN">
								<input type="hidden" name="add" id="adda_action" value="add">
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal" onclick="subbris()">Cancel</button>
									<button type="submit" class="btn btn-primary">Add</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

			<!-- Admin table-->
			<table id="table" class="table table-bordered table-hover">
				<tr>
					<th>Username</th>
					<th>Firstname</th>
					<th>Lastname</th>
					<th>Email</th>
					<th>Telephone</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</table>

		</div>

		<!-- Bootstrap delete modal for Admin-->
		<div class="modal fade" id="delete_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel2">
			<div class="modal-dialog" role="document">
				<div class="modal-content col-md-6">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true"> &times; </span>
						</button>
						<p class="modal-title" id="myModalLabel2">Admin will be
							deleted!</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-danger active" onclick="delete_()"
							data-dismiss="modal">Yes</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Bootstrap update modal for Admin-->
		<div class="modal fade" id="updatea_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel3">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel3">Edit admin</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="javascript:update()">
							<input type="hidden" id="update_id">
							<div class="form-group">
								<label class="col-md-2 control-label">Username</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user"></i></span><input
											name="update_username" id="update_username"
											placeholder="Username" class="form-control" type="text"
											readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">First Name</label>
								<div class="col-md-10  inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user bl"></i></span> <input
											name="update_firstName" id="update_firstName"
											placeholder="First Name" class="form-control" type="text">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Last Name</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user gr"></i></span> <input
											name="update_lastName" id="update_lastName"
											placeholder="Last Name" class="form-control" type="text">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Email</label>
								<div class="col-md-10  inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-envelope"></i></span> <input
											name="update_email" id="update_email" placeholder="Email"
											class="form-control" type="text">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Telephone</label>
								<div class="col-md-10  inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-earphone"></i></span> <input
											name="update_telephone" id="update_telephone"
											placeholder="Telephone" class="form-control" type="number">
									</div>
								</div>
							</div>
							<input type="hidden" name="edit" id="edit_action" value="edit">
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancel</button>
								<button type="submit" class="btn btn-primary">Save
									Changes</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<!-- Bootstrap info modal for Admin-->
		<div class="modal fade" id="info_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel4">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel4">Admin Info</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-md-2 control-label">User Id</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-list"></i></span><input name="info_id"
											id="info_id" placeholder="Id" class="form-control"
											type="text" readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Username</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user"></i></span><input
											name="info_username" id="info_username"
											placeholder="Username" class="form-control" type="text"
											readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">First Name</label>
								<div class="col-md-10  inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user bl"></i></span> <input
											name="info_firstName" id="info_firstName"
											placeholder="First Name" class="form-control" type="text"
											readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Last Name</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user gr"></i></span> <input
											name="info_lastName" id="info_lastName"
											placeholder="Last Name" class="form-control" type="text"
											readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Email</label>
								<div class="col-md-10  inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-envelope"></i></span> <input
											name="info_email" id="info_email" placeholder="Email"
											class="form-control" type="text" readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Telephone</label>
								<div class="col-md-10  inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-earphone"></i></span> <input
											name="info_telephone" id="info_telephone"
											placeholder="Telephone" class="form-control" type="number"
											readonly>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">OK</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="centrlink">
			<a href="${pageContext.request.contextPath}/admin">Back to admin
				page</a>
		</div>
	</div>
	<script type="text/javascript">
		//validation function for add admin	
		$(document).ready(function() {

			$("#adda_form").validate({

				errorClass : "my-error-class",
				rules : {
					adda_username : {
						required : true,
						minlength : 4
					},
					adda_password : {
						required : true,
						minlength : 4
					},
					adda_firstName : {
						required : true,
						minlength : 1
					},
					adda_lastName : {
						required : true,
						minlength : 1
					},
					adda_email : {
						required : true,
						minlength : 2
					},
					adda_telephone : {
						required : true,
						minlength : 6
					}
				},
				messages : {
					adda_username : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 4 character"
					},
					adda_password : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 4 character"
					},
					adda_firstName : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 2 characters"
					},
					adda_lastName : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 2 character"
					},
					adda_email : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 2 character"
					},
					adda_telephone : {
						required : "Please enter only numbers",
						minlength : "Your data must be at least 6 numbers"
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