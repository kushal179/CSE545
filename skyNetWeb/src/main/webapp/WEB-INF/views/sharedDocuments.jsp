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
<link href="<c:url value="/resources/favicon.ico" />" rel="icon" type="image/x-icon" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery/jquery.js" />"></script>

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
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="changePassword"></a>Change Password</li>
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
																								String deptDesc = deptMap.get(departmentId).getDeptDesc();
																								request.setAttribute("departDesc", deptDesc);
																								request.setAttribute("departId", departmentId);
						%>
						<li class="liClass"><a
							href="Dashboard?deptId=${departId}&folderId=-1">${departDesc}</a></li>
						<%
							}
						%>
						<li class="nav-header">Shared Documents</li>
						<li><a href="#">With You</a></li>
						<li><a href="SharedByDocements?folderId=-1">By You</a></li>
					</ul>
				</div>
			</div>

			<div class="span9">
				<noscript>
					<div class="hero-unitops">
						<p class="text-error">
							<b>JavaScript is turned off in your web browser. Turn it on
								to take full advantage of this site, then refresh the page.</b>
						</p>
					</div>
				</noscript>
				<div class="hero-unittitle">
					<h3>Shared By You</h3>
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
					<button class="btn-link" type="button" id="upload-button">
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

				<div id="upload-bar" class="hero-unitops" style="display: none;">
					<form id="upload-submit" action="/upload" method="post"
						enctype="multipart/form-data">

						<table cellpadding="5">
							<tr>
								<td><input type="file" name="file" id="file-upload"></td>
								<td><input type="checkbox" id="enable-encryption" /></td>
								<td><input type="hidden" id="parent-file-id"
									value="${parentFileId }"></td>
								<td><input type="hidden" id="dept-id" value="${deptId }"></td>
								<td><label for="ency">&nbsp;&nbsp;Encrypt file</label></td>
								<td><input disabled="disabled" type="password"
									name="encrypt" id="password-field"></td>
								<td><input type="submit" name="Upload" value="Upload" /></td>
							</tr>
						</table>

					</form>
				</div>

				<div class="row-fluid">
					<table class="table table-hover">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Shared By</th>
								<th>Modification Date</th>
								<th>File Type</th>
								<th>Locked</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${files}">
								<tr onclick="selectFileRow(this);">
									<td width="40px"><img height="30px" width="30px"
										src="${item.iconFile}"></td>
									<td><a href="${item.hyperlink }">${item.fileName}</a></td>
									<td>${sharedByName}</td>
									<td>${item.modTime}</td>
									<td>${item.type}</td>
									<td><c:choose>
											<c:when test="${item.lock}">
												<img height="25px" width="25px"
													src="resources/icons/lock.jpg" />
											</c:when>
										</c:choose></td>
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
			<p>© SkyNet - Company 2012</p>
		</footer>

	</div>
	<script type="text/javascript"
		src="<c:url value="/resources/js/dashboard.js" />"></script>
</body>
</html>