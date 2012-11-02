<%@page import="com.asu.edu.base.vo.RegisterationVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<html lang="en"><head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <title>Administrator Operations</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet"> 
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
                  src="<c:url value="/resources/js/commonprj.js" />"></script>
    </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">Doc Kloud</a>
          <div class="nav-collapse collapse">
            <p class="navbar-text pull-right">
              Logged in as <a href="#" class="navbar-link">Admin</a>
            </p>
            <ul class="nav">
              <li class="active"><a href="#">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Logout</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul  class="nav nav-tabs nav-stacked">
              <li id="request" class="nav-header">Requests</li>
              <li id="pending"><a href="/edu/admin">Pending</a></li>
              <li class="nav-header">Users</li>
              <li id="copMgr"><a href="/edu/admin-copMgr">Corporate Level</a></li>
              <li id="deptMgr"><a href="/edu/admin-deptMgr">Department Managers</a></li>
              <li id="regEmp"><a href="/edu/admin-regularEmp">Regular Employees</a></li>
              <li id="guestUsr"><a href="/edu/admin-guest">Guest Users</a></li>
              <li class="nav-header">Operations</li>
              <li><a href="/edu/admin-logs">System Log</a></li>
              <li><a href="#">Back-up</a></li>
            </ul>
          </div>
        </div>
        <script type="text/javascript">
       function highlight(){
    	    var data = '${role_id}';
       		var obj = document.getElementById(data);
       		obj.setAttribute("class", "active");
       };
       </script>
       <script>
		//call after page loaded
		window.onload=highlight; 
		</script>
        <div class="span9">
          <div class="hero-unittitle">
            <h3>Users - Regular Employees</h3>
          </div>
          
          <div class="hero-unitops" id="actionbar">
          	Select users
          </div>
          <div class="row-fluid">
          <table class="table table-hover"  id="contentsTable">
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
              	  <tr onclick="selectUsersRow(this);">
              	  <c:set var="count" value="${count + 1}" scope="page"/>  
                  <td>${count}</td>
                  <td>${item.userName}</td>
                  <td>${item.department}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>

	<br/><br/><br/>
      <hr>
      <footer>
        <p>© SkyNet - Company 2012</p>
      </footer>

    </div>
</body></html>