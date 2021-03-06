<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8">
<title>Administrator Operations</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />"
	rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
<script type="text/javascript"
	src="<c:url value="/resources/jquery/jquery.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/commonprj.js" />"></script>
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Doc Kloud</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">Welcome
						${sessionScope["userVO"].firstName}
						${sessionScope["userVO"].lastName}</p>
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="changePassword">Change Password</a></li>
						<li><a href="<c:url value="/j_spring_security_logout" />">Logout</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span3">
				<div class="well sidebar-nav">
					<ul class="nav nav-tabs nav-stacked">
						<li class="nav-header">Requests</li>
						<li><a href="admin">Pending</a></li>
						<li class="nav-header">Users</li>
						<li id="copMgr"><a href="admin-copMgr">Corporate Level</a></li>
						<li id="deptMgr"><a href="admin-deptMgr">Department
								Managers</a></li>
						<li id="regEmp"><a href="admin-regularEmp">Regular
								Employees</a></li>
						<li id="guestUsr"><a href="admin-guest">Guest Users</a></li>
						<li class="nav-header">Operations</li>
						<li class="active"><a href="admin-logs">System Log</a></li>
						<li><a href="admin-backup">Back-up</a></li>
					</ul>
				</div>
			</div>

			<div class="span9">
				<div class="hero-unittitle">
					<h3>System Logs</h3>
				</div>

				<div class="hero-unitops" id="actionbar">Download Files</div>
				<div class="row-fluid">
					<table class="table table-hover" id="contentsTable">
						<thead>
							<tr>
								<th>#</th>
								<th>PathName</th>
								<th>ModifiedDate</th>
							</tr>
						</thead>
						<tbody id="contentsTableBody">
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="item" items="${logfiles}">
								<tr>
									<c:set var="count" value="${count + 1}" scope="page" />
									<td>${count}</td>
									<td><a href="${item.hyperLink}">${item.pathName}</a></td>
									<td>${item.modifiedDate}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<br />
		<br />
		<br />
		<hr>
		<footer>
			<p>© SkyNet - Company 2012</p>
		</footer>

	</div>
</body>
</html>