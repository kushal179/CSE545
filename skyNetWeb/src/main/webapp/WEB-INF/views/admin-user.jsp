<%@page import="com.asu.edu.base.vo.FileVO"%>
<%@page import="com.asu.edu.base.vo.DepartmentVO"%>
<%@page import="com.asu.edu.cache.MasterCache"%>
<%@page import="org.apache.taglibs.standard.tei.ForEachTEI"%>
<%@page import="com.asu.edu.base.vo.UserVO"%>
<%@page import="com.asu.edu.constants.CommonConstants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="com.asu.edu.base.vo.RegisterationVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.asu.edu.base.vo.PendingUsersVO"%>
<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="utf-8">
<title>Administrator Operations</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Le styles -->
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/favicon.ico" />" rel="icon" type="image/x-icon" />
<script src="<c:url value="/resources/jquery/jquery.js"/>"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
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
	src="<c:url value="/resources/bootstrap/js/bootstrap-modal.js" />"></script>
</head>

<body onload="loadbody('${role_id}')">

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="#">Doc Kloud</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						Logged in as <a href="#" class="navbar-link">Admin</a>
					</p>
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Logout</a></li>
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
						<li id="request" class="nav-header">Requests</li>
						<li id="pending"><a href="admin">Pending</a></li>
						<li class="nav-header">Users</li>
						<li id="copMgr"><a href="admin-copMgr">Corporate Level</a></li>
						<li id="deptMgr"><a href="admin-deptMgr">Department
								Managers</a></li>
						<li id="regEmp"><a href="admin-regularEmp">Regular
								Employees</a></li>
						<li id="guestUsr"><a href="admin-guest">Guest Users</a></li>
						<li class="nav-header">Operations</li>
						<li><a href="admin-logs">System Log</a></li>
						<li><a href="#">Back-up</a></li>
					</ul>
				</div>
			</div>

			<div class="span9">
				<div class="hero-unittitle" id="headerbar"
					onclick="onheaderBarClicked();">
					<h3>Users - Regular Employees</h3>
				</div>

				<div class="hero-unitops" id="selectItem">Select User</div>
				<div class="hero-unitops" id="itemSelected">
					<table>
						<tr>
							<td width="80%"><label id="itemname"></label></td>
							<td>
								<form action="admin/users/remove" method="post">
									<button id="removebtn" class="btn-link" name="userid"
										type="submit">
										<i class="icon-remove"></i>Reject
									</button>
								</form>
							</td>
						</tr>
					</table>
				</div>

				<div class="row-fluid">
					<table class="table table-hover" id="contentsTable">
						<thead>
							<tr>
								<th>#</th>
								<th>Name</th>
								<th>Department</th>
							</tr>
						</thead>
						<tbody id="contentsTableBody">
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="item" items="${users}">
								<tr onclick="selectApprovedUsersRow(this);">
									<c:set var="count" value="${count + 1}" scope="page" />
									<td>${count}</td>
									<td>${item.userName}</td>
									<td style="display: none;">${item.userId}</td>
									<c:set var="deptName" value="" scope="page" />
									<td><c:forEach var="name" items="${item.deptNames}">
											<c:set var="deptName" value="${name}" scope="page" />
										${deptName},
									</c:forEach></td>
									<td style="display: none;"><c:forEach var="deptid"
											items="${item.deptIds}">
											<c:set var="id" value="${deptid}" scope="page" />
										${id},
									</c:forEach></td>
									<td style="display: none;">${item.roleId}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<br /> <br /> <br />
		<hr>
		<footer>
			<p>© SkyNet - Company 2012</p>
		</footer>

	</div>
		<script type="text/javascript"
		src="<c:url value="/resources/js/commonprj.js" />"></script>
</body>
</html>