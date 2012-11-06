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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/bootstrap/js/bootstrap-modal.js" />"></script>
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
						${sessionScope["userVO"].userName}
						${sessionScope["userVO"].lastName}</p>
					<ul class="nav">
						<li class="active"><a href="/">Home</a></li>
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
								String deptDesc = deptMap.get(departmentId).getDeptDesc();
								request.setAttribute("departDesc", deptDesc);
								request.setAttribute("departId", departmentId);
								if (departmentId == request.getAttribute("deptId"))
									request.setAttribute("liClass", "active");
								else
									request.setAttribute("liClass", "");
						%>
						<li class="${liClass }"><a
							href="Dashboard?deptId=${departId}&folderId=-1">${departDesc}</a></li>
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
						<p class="text-error">
							<b>JavaScript is turned off in your web browser. Turn it on
								to take full advantage of this site, then refresh the page.</b>
						</p>
					</div>
				</noscript>
				<div class="hero-unittitle" id="headerbar"
					onclick="onheaderBarClicked();">
					<table>
						<tr>
							<td width="70%">
								<h3>${deptDesc}</h3>
							</td>
							<td>
								<button class="btn-link" type="button">
									<i class="icon-file"></i>Upload New File
								</button>
							</td>
							<td>
								<button class="btn-link" type="button">
									<i class="icon-folder-close"></i>New Folder
								</button>
							</td>
						</tr>
					</table>
				</div>

				<div class="hero-unitops" id="selectItem">Select Item</div>

				<div class="hero-unitops" id="itemSelected">
					<table>
						<tr>
							<td width="50%"><label id="itemname"></label></td>
							<td width="10%">
								<button class="btn-link" type="button">
									<i class="icon-download"></i>Read
								</button>
							</td>
							<td width="10%">
								<button class="btn-link" type="button" id="upload-button">
									<i class="icon-upload"></i>Update
								</button>
							</td>
							<td width="10%">
								<button class="btn-link" type="button">
									<i class="icon-lock"></i>Check-Out
								</button>
							</td>
							<td width="10%"><a href="#shareModal" class="btn-link"
								data-toggle="modal"><i class="icon-share"></i>Share</a></td>
							<td width="10%">
								<button class="btn-link" type="button">
									<i class="icon-remove"></i>Delete
								</button>
							</td>
						</tr>
					</table>
				</div>

				<div id="upload-bar" class="hero-unitops" style="display: none;">
					<form id="upload-submit" action="upload" method="post"
						enctype="multipart/form-data">

						<table cellpadding="5">
							<tr>
								<td><input type="file" name="file" id="file-upload"></td>
								<td><input type="checkbox" id="enable-encryption" /></td>
								<td><input type="hidden" id="parent-file-id"
									name="parent-file-id" value="${parentFileId }"></td>
								<td><input type="hidden" id="dept-id" name="dept-id"
									value="${deptId }"></td>
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
								<th>Modification Date</th>
								<th>File Type</th>
								<th>Locked</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item" items="${files}">
								<tr onclick="onItemselected(this);">
									<td width="40px"><img height="30px" width="30px"
										src="${item.iconFile}"></td>
									<td><a href="${item.hyperlink }">${item.fileName}</a></td>
									<td>${item.modTime}</td>
									<td>${item.type}</td>
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

		<form:form action="shareComponent" modelAttribute="shareVO">
			<!-- Modal -->
			<div id="shareModal" class="modal hide fade" tabindex="-1"
				role="dialog" aria-labelledby="shareModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"></button>
					<h3 id="myModalLabel">Share Item</h3>
				</div>
				<div class="modal-body">
					<table>
						<tr>
							<td align="right">From User:</td>
							<td style="padding-left: 20px;"><label id="userName">${sessionScope["userVO"].userName}</label></td>
							<td style="display: none;"><form:input id="fromUser"
									path="fromUser" /></td>
						</tr>
						<tr>
							<td align="right">To User:</td>
							<td style="padding-left: 20px;"><form:select id="toUser"
									path="toUser">
									<c:forEach var="item" items="${approvedUsers}">
										<form:option value="${item.id}" label="${item.userName}" />
									</c:forEach>
								</form:select></td>
						</tr>
						<tr>
							<td width="40%" align="right">Permissions:</td>
							<td>
								<table>
									<tr>
										<td style="padding-left: 20px;"><form:checkbox
												path="permissions" id="read" value="read" /></td>
										<td style="padding-left: 10px; padding-top: 5px;"><label>Read</label></td>
									</tr>
									<tr>
										<td style="padding-left: 20px;"><form:checkbox
												path="permissions" id="update" value="update" /></td>
										<td style="padding-left: 10px; padding-top: 5px;"><label>Update</label></td>
									</tr>
									<tr>
										<td style="padding-left: 20px;"><form:checkbox
												path="permissions" id="lock" value="lock" /></td>
										<td style="padding-left: 10px; padding-top: 5px;"><label>CheckIn-CheckOut</label></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<form action="shareComponent" method="post">
						<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
						<button class="btn btn-primary">Share</button>
					</form>
				</div>
			</div>
		</form:form>

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