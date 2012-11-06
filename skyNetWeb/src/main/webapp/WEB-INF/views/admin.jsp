<%@page import="com.asu.edu.base.vo.PendingUsersVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
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
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap-modal.js" />"></script>
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
					<p class="navbar-text pull-right">
						Logged in as <a href="#" class="navbar-link">${username}</a>
					</p>
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
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
						<li class="active" id="pending"><a href="admin">Pending</a></li>
						<li class="nav-header">Users</li>
						<li id="copMgr"><a href="admin-copMgr">Corporate
								Level</a></li>
						<li id="deptMgr"><a href="admin-deptMgr">Department
								Managers</a></li>
						<li id="regEmp"><a href="admin-regularEmp">Regular
								Employees</a></li>
						<li id="guestUsr"><a href="admin-guest">Guest Users</a></li>
						<li class="nav-header">Operations</li>
						<li><a href="admin-logs">System Log</a></li>
					</ul>
				</div>
			</div>

			<div class="span9">
				<div class="hero-unittitle">
					<h3>Pending Requests</h3>
				</div>

				<div class="hero-unitops" id="actionbar">Select users</div>

				<div class="row-fluid">
					<table class="table table-hover" id="contentsTable">
						<thead>
							<tr>
								<th>#</th>
								<th>Name</th>
								<th>Role</th>
								<th>Department</th>
							</tr>
						</thead>
						<tbody id="contentsTableBody">
							<c:set var="count" value="0" scope="page" />
							<c:forEach var="item" items="${pendingUsers}">
								<tr onclick="selectUsersRow(this);">
									<c:set var="count" value="${count + 1}" scope="page" />
									<td>${count}</td>
									<td>${item.userName}</td>
									<td style="display: none;">${item.userId}</td>
									<td>${item.roleDesc}</td>
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
									<td style="display: none;"><c:forEach var="item"
											items="${deptList}">
										${item.id},
									</c:forEach></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table class="table">
					</table>
				</div>
			</div>
		</div>

		<form:form action="admin/modifynapprove"
			modelAttribute="modifiedUserVO">
			<!-- Modal -->
			<div id="modifyModal" class="modal hide fade" tabindex="-1"
				role="dialog" aria-labelledby="modifyModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h3 id="myModalLabel">Modify Role and Department</h3>
				</div>
				<div class="modal-body">
					<table>
						<tr>
							<td align="right">User:</td>
							<td style="padding-left: 20px;"><label id="userName"></label></td>
							<td style="display: none;"><form:input id="userId"
									path="userId" /></td>
						</tr>
						<tr>
							<td align="right">Role:</td>
							<td style="padding-left: 20px;"><form:select id="role"
									path="roleId">
									<c:forEach var="item" items="${roleList}">
										<form:option value="${item.id}" label="${item.desc}" />
									</c:forEach>
								</form:select></td>
						</tr>
						<tr>
							<td width="40%" align="right">Department:</td>
							<td>
								<table>
									<c:forEach var="item" items="${deptList}">
										<tr>
											<td style="padding-left: 20px;"><form:checkbox
													path="deptIds" id="dept_${item.id}" value="${item.id}" /></td>
											<td style="padding-left: 20px; padding-top: 5px;"><label>${item.deptName}</label>
											</td>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<form action="admin/modifynapprove" method="post">
						<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
						<button class="btn btn-primary">Approve</button>
					</form>
				</div>
			</div>
		</form:form>

		<br /> <br /> <br />
		<hr>
		<footer>
			<p>© SkyNet - Company 2012</p>
		</footer>

	</div>
</body>
</html>