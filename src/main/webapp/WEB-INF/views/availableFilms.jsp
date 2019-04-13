<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<title>Available films</title>
<script src="<c:url value="/static/js/sockjs-0.3.4.min.js"/>"></script>
<c:url value="/wsevents" var="socketDest" />
</head>
<body onload="connect();">
	<div class="wrapper">
		<div class="container">
			<p class="logUser">
				<span class="glyphicon glyphicon-user lb"></span> <strong>${usernameAdm}</strong>
			</p>
			<b></b>
			<h3 class="bg-info text-info">
				AVAILABLE FILMS - renting film for: <span
					class="glyphicon glyphicon-user lb"></span> <strong>${user3.username}</strong>
			</h3>
			<br> <br>
			<c:if test="${param.rflm == 0}">
				<div class="userInf1">
					<h3 class="text-danger bg-danger">${param.message}</h3>
				</div>
			</c:if>
			<c:if test="${param.rflm == 1}">
				<div class="userInf1">
					<h3 class="text-warning bg-warning">${param.message}</h3>
				</div>
			</c:if>
			<c:if test="${param.rflm ==2}">
				<div class="userInf1">
					<h3 class="text-warning bg-warning">${param.message}</h3>
				</div>
			</c:if>
			<c:if test="${param.rflm==3}">
				<div class="userInf1">
					<h3 class="text-success bg-success">${param.message}</h3>
				</div>
			</c:if>
			<br> <br> <br>
			<table class="table table-bordered table-hover">
				<tr>
					<th>Title</th>
					<th>Genre</th>
					<th>Year</th>
					<th></th>
				</tr>
				<c:forEach var="flm" items="${availableFilmsList}">
					<tr id="${flm.title}">
						<td>${flm.title}</td>
						<td>${flm.genre}</td>
						<td>${flm.year}</td>
						<td><a data-toggle="modal" href="#${flm.id}"
							class="btn btn-primary active">Rent</a>
							<div class="modal fade" id="${flm.id}" tabindex="-1"
								role="dialog" aria-labelledby="myModalLabel">
								<div class="modal-dialog" role="document">
									<div class="modal-content col-md-6">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<h4 class="modal-title" id="myModalLabel">
												User '
												<c:out value="${user3.username}" />
												' wants to rent film: '${flm.title}'
											</h4>
										</div>
										<div class="modal-footer">
											<a
												href="${pageContext.request.contextPath}/admin/rentingFilm/${flm.title}/${user3.username}"
												class="btn btn-success active btn-md" role="button">Confirm</a>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Quit</button>
										</div>
									</div>
								</div>
							</div></td>
					</tr>
				</c:forEach>
			</table>
			<br>
		</div>
		<div class="centrlink">
			<a href="${pageContext.request.contextPath}/admin/rentNewFilm"
				onclick="disconnect()">Back to users</a>
		</div>
		<br> <br> <br>
	</div>
	<script type="text/javascript">
		var ws;
		var pathArray = document.location.pathname.split('/');
		var path = pathArray[0] + '/' + pathArray[1];
		var targetReq = "/admin/rentingFilm/";
		var resultPath = path + targetReq;

		function connect() {
			ws = new SockJS('${socketDest}');
			console.log("Connected " + ws);
			ws.onmessage = function(evt) {
				doEvtAction(evt);
			}
		}

		function disconnect() {
			if (ws != null) {
				ws.close();
			}
			console.log("Disconnected");
		}

		function doEvtAction(evt) {
			var jsonObj = JSON.parse(evt.data);
			var flmId = jsonObj.flmId;
			var title = jsonObj.title;
			var genre = jsonObj.genre;
			var year = jsonObj.year;
			var username = jsonObj.username;
			var action = jsonObj.action;

			if (jsonObj.action === "RENTED") {
				deleteFilmRow(title);
			} else if (jsonObj.action === "RETURNED") {
				addFilmRow(flmId, title, genre, year, username);
			} else {
				alert("Option \"" + jsonObj.action + "\" not available!");
			}
		}

		function deleteFilmRow(title) {
			var tblTd = document.getElementById(title);
			tblTd.parentNode.removeChild(tblTd);
		}

		function addFilmRow(flmId, title, genre, year, username) {
			var resultLink = resultPath + title + '/' + username;
			$('.table')
					.append(
							'<tr id='
						+ title
						+ '> <td>'
									+ title
									+ '</td> <td>'
									+ genre
									+ '</td> <td>'
									+ year
									+ '</td> <td> <a data-toggle="modal" href="#'+flmId+'" class="btn btn-primary active"> Rent </a>'
									+ '<div class="modal fade" id="'
						+ flmId
						+ '" tabindex="-1"'
						+ 'role="dialog" aria-labelledby="myModalLabel">'
									+ '<div class="modal-dialog" role="document">'
									+ '<div class="modal-content col-md-6">'
									+ '<div class="modal-header">'
									+ '<button type="button" class="close" data-dismiss="modal"'
						+ 'aria-label="Close">'
									+ '<span aria-hidden="true">&times;</span>'
									+ '</button>'
									+ '<h4 class="modal-title" id="myModalLabel">User "'
									+ username+'" wants to rent film: "'
									+ title
									+ '"</h4>'
									+ '</div>'
									+ '<div class="modal-footer">'
									+ '<a href="'
						+ resultLink
						+ '" class="btn btn-success active btn-md cnfr" role="button">Confirm</a>'
									+ '<button type="button" class="btn btn-default"'
						+ 'data-dismiss="modal">Quit</button>'
									+ '</div>' + '</div>' + '</div>'
									+ '</div></td></tr>')
		}
	</script>
</body>
</html>
