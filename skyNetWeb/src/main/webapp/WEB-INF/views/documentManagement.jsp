<%@page import="com.asu.edu.base.vo.DepartmentVO"%>
<%@page import="com.asu.edu.cache.MasterCache"%>
<%@page import="org.apache.taglibs.standard.tei.ForEachTEI"%>
<%@page import="com.asu.edu.base.vo.UserVO"%>
<%@page import="com.asu.edu.constants.CommonConstants"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8">
<title>Document Management</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Le styles -->
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>
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
						${sessionScope["userVO"].userName}
						${sessionScope["userVO"].lastName}</p>
					<ul class="nav">
						<li class="active"><a href="/welcome">Home</a></li>
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
						<li class="nav-header">Departments</li>
						<%
							ArrayList<Integer> depts = ((UserVO) session.getAttribute("userVO"))
																					.getDepartments();
																			Map<Integer, DepartmentVO> deptMap = MasterCache.getDepartmentMap();
																			for (Integer departmentId : depts) {
																				String deptName = deptMap.get(departmentId).getDeptName();
																				request.setAttribute("departName", deptName);
																				request.setAttribute("departId", departmentId);
						%>
						<li><a href="/edu/Dashboard/?deptId=${departId}&folderId=-1">${departName}</a></li>
						<%
							}
						%>
						<li class="nav-header">Shared Documents</li>
						<li><a href="#">With You</a></li>
						<li><a href="#">By You</a></li>
					</ul>
				</div>
			</div>

			<div class="span9">
				<noscript>
					<div class="hero-unitops">
						<p class="text-error"><b>JavaScript is turned off in your web
							browser. Turn it on to take full advantage of this site, then
							refresh the page.</b></p>
					</div>
				</noscript>
				<div class="hero-unittitle">
					<h3>Human Resources</h3>
				</div>

				<div class="hero-unitops">
					SalarySchedule.pdf
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn-link" type="button">
						<i class="icon-search icon-download"></i>Read
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn-link" type="button" id="upload_button">
						<i class="icon-search icon-upload"></i>Update
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn-link" type="button">
						<i class="icon-search icon-lock"></i>Check-Out
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn-link" type="button">
						<i class="icon-search icon-share"></i>Share
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="btn-link" type="button">
						<i class="icon-search icon-remove"></i>Delete
					</button>
				</div>

				<div id="upload_bar" class="hero-unitops">
					<input type="file">
					<div style="display: inline-block; width: 100px;">
						<input type="checkbox" id="ency" /><label for="ency">encrypted
							?</label>
					</div>
					<input type="password"> <input type="submit" name="Upload" />
				</div>

				<div class="row-fluid">
					<table class="table table-hover">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Modification Date</th>
								<th>Locked</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${files}">
								<tr onclick="selectUsersRow(this);">
									<td width="40px"><img height="30px" width="30px"
										src="${item.iconFile}"></td>
									<td><a href="${item.hyperlink }">${item.fileName}</a></td>
									<td>${item.modTime}</td>
									<td>${item.lock}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table class="table">
					</table>
				</div>
			</div>
		</div>

		<br /> <br />
		<hr>

		<footer>
			<p>� SkyNet - Company 2012</p>
		</footer>

	</div>
	<script>
		$("#upload_button").click(function() {
			$("#upload_bar").toggle("slow");
		});
	</script>
</body>
</html>