<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tld/timeFunctions" prefix="ctfun"%>
<html>
<head>
<%@include file="header.jsp"%>
<meta name="_csrf" content="${_csrf.token}" />
<title>Rented Films</title>
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
			<h3 class="title3 bg-info text-info">RENTED FILMS</h3>
			<br> <br>
			<c:if test="${param.message != null}">
				<div class="userInf1">
					<h3 class="text-success bg-success">${param.message}</h3>
				</div>
			</c:if>
			<br> <br> <br>
			<table class="table table-bordered table-hover">
				<tr>
					<th>Film Id</th>
					<th>Title</th>
					<th>User Id</th>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Rent Start</th>
					<th>NOTE</th>
					<th>Remaining Time</th>
					<th></th>
				</tr>
				<c:forEach var="flm" items="${rentedFilmsList}">
					<tr>
						<td>${flm.id}</td>
						<td>${flm.title}</td>
						<c:forEach var="user" items="${flm.users}">
							<td>${user.id}</td>
							<td>${user.username}</td>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<c:set var="uusername" value="${user.username}" />
						</c:forEach>
						<td>${ctfun:convertTime(flm.timeFilmrent)}</td>
						<c:if test="${ctfun:remainOrExceed(flm.timeFilmrent, currTime)}">
							<td>
								<p class="text-success bg-success">
									<c:out value="Remaining" />
								<p>
							</td>
						</c:if>
						<c:if
							test="${not ctfun:remainOrExceed(flm.timeFilmrent, currTime)}">
							<td>
								<p class="text-danger bg-danger">
									<c:out value="!!!Exceeded" />
								<p>
							</td>
						</c:if>
						<td>${ctfun:remainingTime(flm.timeFilmrent, currTime)}</td>
						<td><a data-toggle="modal" href="#${flm.id}"
							class="btn btn-primary active">Return</a>
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
												<c:out value="${uusername}" />
												' returns film: '${flm.title}'
											</h4>
										</div>
										<div class="modal-footer">
											<a
												href="${pageContext.request.contextPath}/admin/getBackFilm/${flm.title}"
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
			<div class="centrlink">
				<a href="${pageContext.request.contextPath}/admin"
					onclick="disconnect()">Back to admin page</a>
			</div>
			<br> <br> <br>
		</div>
	</div>
	<script type="text/javascript">
		var timeFunc;
		var tmilisec = 120000;

		function connect() {
			timeFunc = setTimeout("location.reload(true);", tmilisec);			
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
			clearTimeout(timeFunc);
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
				addFilmRowByReload();
			} else if (jsonObj.action === "RETURNED") {				
				deleteFilmRow(title);
			} else {
				alert("Option \"" + jsonObj.action + "\" not available!");
			}
		}

		function deleteFilmRow(title) {			
			var tblTd = document.getElementById(title);
			tblTd.parentNode.removeChild(tblTd);
		}

		function addFilmRowByReload() {
			clearTimeout(timeFunc);
			location.reload(true);
			timeFunc = setTimeout("location.reload(true);", tmilisec);
		}
	</script>
</body>
</html>