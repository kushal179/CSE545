<html lang="en"><head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <title>Document Management</title>
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
              Logged in as <a href="#" class="navbar-link">CEO</a>
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
              <li class="nav-header">Departments</li>
              <li class="active"><a href="#">Human resources</a></li>
              <li><a href="#">Logistic and supply</a></li>
              <li><a href="#">IT support</a></li>
              <li><a href="#">Sales and promotion</a></li>
              <li><a href="#">Research and development</a></li>
              <li><a href="#">Finance</a></li>
              <li><a href="#">Company management</a></li>
              <li class="nav-header">Shared Documents</li>
              <li><a href="#">With You</a></li>
              <li><a href="#">By You</a></li>
            </ul>
          </div>
        </div>
        
        <div class="span9">
          <div class="hero-unittitle">
            <h3>Human Resources</h3>
          </div>
          
          <div class="hero-unitops">
          	SalarySchedule.pdf
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
          	<button class="btn-link" type="button"><i class="icon-search icon-download"></i>Read</button>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<button class="btn-link" type="button"><i class="icon-search icon-upload"></i>Update</button>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<button class="btn-link" type="button"><i class="icon-search icon-lock"></i>Check-Out</button>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<button class="btn-link" type="button"><i class="icon-search icon-share"></i>Share</button>
          	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          	<button class="btn-link" type="button"><i class="icon-search icon-remove"></i>Delete</button>
          </div>

          <div class="row-fluid">
          	<table class="table table-hover">
              <thead>
                <tr>
                  <th>#</th>
                  <th>Name</th>
                  <th>Type</th>
                  <th>Modified</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1</td>
                  <td>Strategies</td>
                  <td>Folder</td>
                  <td>8/22/2012</td>
                </tr>
                <tr>
                  <td>2</td>
                  <td>SalarySchedule.pdf</td>
                  <td>File</td>
                  <td>9/20/2012</td>
                </tr>
                <tr>
                  <td>3</td>
                  <td>EmployeeList.pdf</td>
                  <td>File</td>
                  <td>9/10/2012</td>
                </tr>
              </tbody>
            </table>
          	<table class="table">
			</table>
          </div>
        </div>
      </div>

	<br/><br/>
      <hr>

      <footer>
        <p>© SkyNet - Company 2012</p>
      </footer>

    </div>
</body></html>