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
            <ul class="nav nav-tabs nav-stacked">
              <li class="nav-header">Requests</li>
              <li><a href="#">Pending</a></li>
              <li class="nav-header">Users</li>
              <li><a href="#">Corporate Level</a></li>
              <li><a href="#">Department Managers</a></li>
              <li><a href="#">Regular Employees</a></li>
              <li><a href="#">Guest Users</a></li>
              <li class="nav-header">Operations</li>
              <li class="active"><a href="#">System Log</a></li>
              <li><a href="#">Back-up</a></li>
              <li><a href="#">Index</a></li>
            </ul>
          </div>
        </div>
        
        <div class="span9">
          <div class="hero-unittitle">
            <h3>System Logs</h3>
          </div>
          
          <div class="hero-unitops">
          	9/10/2012_System_log.txt
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
          	<button class="btn-link" type="button"><i class="icon-search icon-download"></i>Download</button>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<button class="btn-link" type="button"><i class="icon-search icon-remove"></i>Delete</button>
          </div>

          <div class="row-fluid">
          	<table class="table table-hover">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Name</th>
                  <th>Modified</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>9/10/2012_System_log.txt</td>
                  <td>9/10/2012</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>9/11/2012_System_log.txt</td>
                  <td>9/11/2012</td>
                </tr>
                                <tr>
                  <td>3</td>
                  <td>9/12/2012_System_log.txt</td>
                  <td>9/12/2012</td>
                </tr>
                                <tr>
                  <td>4</td>
                  <td>9/13/2012_System_log.txt</td>
                  <td>9/13/2012</td>
                </tr>
                                <tr>
                  <td>5</td>
                  <td>9/14/2012_System_log.txt</td>
                  <td>9/14/2012</td>
                </tr>
                                <tr>
                  <td>6</td>
                  <td>9/15/2012_System_log.txt</td>
                  <td>9/15/2012</td>
                </tr>
                                <tr>
                  <td>7</td>
                  <td>9/16/2012_System_log.txt</td>
                  <td>9/16/2012</td>
                </tr>
                                <tr>
                  <td>8</td>
                  <td>9/17/2012_System_log.txt</td>
                  <td>9/17/2012</td>
                </tr>
                <tr>
                  <td>9</td>
                  <td>9/18/2012_System_log.txt</td>
                  <td>9/18/2012</td>
                </tr>
              </tbody>
            </table>
          	<table class="table">
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