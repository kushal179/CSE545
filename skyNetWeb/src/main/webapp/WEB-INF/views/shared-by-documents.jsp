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

<body onload="sharedBybodyload()">

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
						<li><a href="SharedToYouDocuments?folderId=-1">With You</a></li>
						<li class="active"><a href="SharedByYouDocuments?folderId=-1">By
								You</a></li>
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
				<div class="hero-unittitle" id="headerbar"
					onclick="onheaderBarClicked();">
					<h3>Shared By You</h3>
				</div>

				<div class="hero-unitops" id="selectItem">Select Item</div>

				<div class="hero-unitops" id="itemSelected">
					<table>
						<tr>
							<td width="70%"><label id="itemname"></label></td>
							<td><form action="unshare" method="post">
									<button id="unsharebtn" class="btn-link" type="submit">
										<i class="icon-share"></i>Unshare
									</button>
									<input type="hidden" id="to-user-id" name="userIdTo" /> <input
										type="hidden" id="file-id" name="fileid" />
								</form></td>
						</tr>
					</table>
				</div>

				<div class="row-fluid">
					<table class="table table-hover">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Shared To</th>
								<th>Modification Date</th>
								<th>File Type</th>
								<th>Locked</th>
							</tr>
						</thead>
						<tbody id="fileslist">
							<c:forEach var="item" items="${files}">
								<tr onclick="onSharedByItemselected(this);">
									<td width="40px"><img height="30px" width="30px"
										src="${item.iconFile}"></td>
									<td><a href="${item.hyperlink }">${item.fileName}</a></td>
									<td>${item.sharedToName}</td>
									<td>${item.modTime}</td>
									<td>${item.type}</td>
									<td style="display: none;" id="selectedfileid">${item.hashedId}</td>
									<td style="display: none;" id="sharetouserid">${item.sharedToId}</td>
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