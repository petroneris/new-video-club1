<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<spring:url value="/static/js/allFilms.js" var="allFilms" />
<script src="${allFilms}"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.12.0/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.12.0/additional-methods.js"></script>
<title>All Films</title>
</head>
<body onload="load();">
	<div class="wrapper">
		<div class="container">
		<p class="logUser"><span class="glyphicon glyphicon-user lb"></span> <strong>${usernameAdm}</strong></p>  		
		<b></b>
			<h3 class="title3">FILMS</h3>
			<div class="addButt">
				<button class="btn btn-success" data-toggle="modal"
					data-target="#addf_modal">Add new Film</button>
			</div>
			<!-- Bootstrap add modal for new Film-->
			<div class="modal fade" id="addf_modal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel1">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel1">Add new Film</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" action="javascript:submitt()"
								id="addf_form">
								<div class="form-group">
									<label class="col-md-2 control-label">Title</label>
									<div class="col-md-10 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-film"></i></span><input name="title"
												id="title" placeholder="Title" class="form-control"
												type="text">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">Genre</label>
									<div class="col-md-10 inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-facetime-video"></i></span> <input
												name="genre" id="genre" placeholder="Genre"
												class="form-control" type="text">
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">Year</label>
									<div class="col-md-10  inputGroupContainer">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-calendar"></i></span> <input name="year"
												id="year" placeholder="Year" class="form-control"
												type="number">
										</div>
									</div>
								</div>
								<input type="hidden" name="add" id="addf_action" value="add">
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

			<!-- All Films table-->
			<table id="table" class="table table-bordered table-hover">
				<tr>
					<th>Title</th>
					<th>Genre</th>
					<th>Year</th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
			</table>
		</div>

		<!-- Bootstrap delete modal for Film-->
		<div class="modal fade" id="delete_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel2">
			<div class="modal-dialog" role="document">
				<div class="modal-content col-md-6">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true"> &times; </span>
						</button>
						<p class="modal-title" id="myModalLabel2">Film will be
							deleted.</p>
					</div>
					<div class="modal-footer">
						<button class="btn btn-danger active" onclick="delete_()"
							data-dismiss="modal">Yes</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Bootstrap update modal for Film-->
		<div class="modal fade" id="update_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel3">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel3">Edit film</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="javascript:update()">
							<input type="hidden" id="update_id">
							<div class="form-group">
								<label class="col-md-2 control-label">Title</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-film"></i></span><input
											name="update_title" id="update_title" placeholder="Title"
											class="form-control" type="text" readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Genre</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-facetime-video"></i></span> <input
											name="update_genre" id="update_genre" placeholder="Genre"
											class="form-control" type="text">
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Year</label>
								<div class="col-md-10  inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span> <input
											name="update_year" id="update_year" placeholder="Year"
											class="form-control" type="number">
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

		<!-- Bootstrap info modal for Film-->
		<div class="modal fade" id="info_modal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel4">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel4">Film Info</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal">
							<div class="form-group">
								<label class="col-md-2 control-label">Film Id</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-list"></i></span> <input
											name="info_id" id="info_id" placeholder="Film Id"
											class="form-control" type="number" readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Title</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-film"></i></span><input name="info_title"
											id="info_title" placeholder="Title" class="form-control"
											type="text" readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Genre</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-facetime-video"></i></span> <input
											name="info_genre" id="info_genre" placeholder="Genre"
											class="form-control" type="text" readonly>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label">Year</label>
								<div class="col-md-10 inputGroupContainer">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span> <input
											name="info_year" id="info_year" placeholder="Year"
											class="form-control" type="number" readonly>
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default"
									data-dismiss="modal">Close</button>
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
	
	//validation function for add film	
		$(document).ready(function() {

			$("#addf_form").validate({

				errorClass : "my-error-class",
				rules : {
					title : {
						required : true,
						minlength : 1
					},
					genre : {
						required : true,
						minlength : 2
					},
					year : {
						required : true,
						minlength : 4
					}

				},
				messages : {
					title : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 1 character"
					},

					genre : {
						required : "The filed cannot be empty",
						minlength : "Your data must be at least 2 character"
					},

					year : {
						required : "Please enter only numbers",
						minlength : "Your data must be at least 4 numbers"
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