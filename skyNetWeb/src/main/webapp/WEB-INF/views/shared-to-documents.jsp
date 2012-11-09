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

<body onload="bodyload()">

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
						<li class="active"><a href="SharedToYouDocuments?folderId=-1">With
								You</a></li>
						<li><a href="SharedByYouDocuments?folderId=-1">By You</a></li>
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
					<h3>Shared To Others</h3>
				</div>

				<div class="hero-unitops" id="selectItem">Select Item</div>

				<div class="hero-unitops" id="itemSelected">
					<table>
						<tr>
							<td width="30%"><label id="itemname"></label></td>
							<td width="10%" style="padding-top: 15px" id="update-button">
								<form>
									<a href="#update-bar" class="btn-link" data-toggle="modal">
										<i class="icon-upload"></i>Update
									</a>
								</form>
							</td>
							<td width="15%" style="padding-top: 15px" id="lock-button">
								<form action="lock" valign="middle" method="post">
									<input id="lock-file-id" name="file-id" type="hidden">
									<i class="icon-lock"></i><input type="submit" value="Lock"
										class="btn-link" />
								</form>
							</td>
							<td width="15%" style="padding-top: 15px" id="unlock-button">
								<form action="unlock" method="POST">
									<input id="unlock-file-id" name="file-id" type="hidden">
									<i class="icon-lock"></i><input type="submit" value="Unlock"
										class="btn-link" />
								</form>
							</td>
							<td width="10%" style="padding-top: 15px" id="delete-button">
								<form action="delete" method="post">
									<input id="delete-file-id" name="file-id" type="hidden">
									<i class="icon-remove"></i><input type="submit" value="Delete"
										class="btn-link" />
								</form>
							</td>

							<td width="10%" style="padding-top: 15px" id="version-button">
								<form action="versions" method="post">
									<input id="version-file-id" name="file-id" type="hidden">
									<i class="icon-time"></i><input type="submit" value="Versions"
										class="btn-link" />
								</form>
							</td>
						</tr>
					</table>
				</div>

				<div class="modal hide fade" role="dialog"
					aria-labelledby="shareModalLabel" aria-hidden="true"
					id="update-bar" style="display: none;" onshow="alert('shown');">

					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
						<h3 id="myModalLabel">Update file</h3>
					</div>

					<form id="update-form" action="upload" method="post"
						enctype="multipart/form-data">
						<div class="modal-body">

							<input type="file" name="file" id="file-upload"><br>
							<table>
								<tr>
									<td><input type="checkbox" id="enable-encryption" /></td>
									<td><label>Encrypt file</label></td>
								</tr>
								<tr>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td><label for="password-field">Password : </label></td>
									<td><input disabled="disabled" type="password"
										name="encrypt" id="password-field" /></td>
								</tr>
							</table>
							<input type="hidden" id="update-file-id" name="file-id" /> <input
								type="hidden" id="dept-id" name="dept-id" value="${deptId }" />
							<input type="hidden" id="parent-file-id" name="parent-file-id"
								value="${parentFileId }">
						</div>

						<div class="modal-footer">
							<input class="btn btn-primary" type="submit" name="Update"
								value="Update" />
							<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
						</div>
					</form>
				</div>

				<div class="row-fluid">
					<table class="table table-hover" id="fileslist">
						<thead>
							<tr>
								<th></th>
								<th>Name</th>
								<th>Modification Date</th>
								<th>File Type</th>
								<th>Locked</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${files}">
								<tr onclick="onDashboardItemselected(this);">
									<td width="40px"><img height="30px" width="30px"
										src="${item.iconFile}"></td>
									<td><a href="${item.hyperlink }">${item.fileName}</a></td>
									<td>${item.modTime}</td>
									<td>${item.type}</td>
									<td><c:choose>
											<c:when test="${item.lock}">
												<img height="25px" width="25px"
													src="resources/icons/lock.jpg" />
											</c:when>
										</c:choose></td>
									<td style="display: none;" id="selectedfileid">${item.hashedId}</td>
									<td style="display: none" id="update-perm">${item.updateAllowed}</td>
									<td style="display: none" id="lock-perm">${item.lockAllowed}</td>
									<td style="display: none" id="is-locked">${item.lock}</td>
									<td style="display: none" id="is-dir">${item.dir}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<table class="table">
					</table>
				</div>

			</div>
		</div>

	</div>

	<br />
	<br />
	<hr>

	<footer>
		<p>© SkyNet - Company 2012</p>
	</footer>

	<script type="text/javascript"
		src="<c:url value="/resources/js/dashboard.js" />"></script>
</body>
</html>