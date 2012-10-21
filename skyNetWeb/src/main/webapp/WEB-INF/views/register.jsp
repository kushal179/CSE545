<html lang="en"><head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <meta charset="utf-8">
    <title>Register</title>
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
            <ul class="nav">
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Login</a></li>
              <li class="active"><a href="#contact">Register</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

	<center>
	<table border="0" cellpadding="0" cellspacing="0">
	<tbody>
		<tr style="height:250px"><td style="width:450px;background-color:#333333" valign="top"><br>
		<center>
		<table border="0" cellpadding="10" cellspacing="10"><tbody><tr><td>
		<table border="0" cellpadding="3" cellspacing="3">
		<tbody><tr><td colspan="2"><div align="left" style="font-size:22px;color:#ffffff">Register</div><br><br></td></tr>
		<tr><td style="color:white">Name:</td><td><input name="" type="text" ></td></tr>
		<tr><td style="color:white">Email:</td><td><input name="" type="text"></td></tr>	
		<tr><td style="color:white">Username:</td><td><input name="" type="text" ></td></tr>
		<tr><td style="color:white">Password:</td><td><input name="" type="password"></td></tr>	
		<tr><td style="color:white">Retype Password:</td><td><input name="" type="password"></td></tr>
		<tr><td style="color:white">Role:</td><td><select name="" type="text"></td></tr>
		<tr><td style="color:white">Department:</td><td><select name="" type="text"></td></tr>
		<tr><td></td><td><div align="left">
		<img src="bootstrap/img/captcha.png"/><br/><br/>
		<button class="btn btn-primary" type="button">Register</button><br/><br/>
		</tbody></table><br>
		</td></tr></tbody></table>
		</center>
		</td></tr></tbody></table>
		</center>

	<br/><br/><br/>
      <hr>
      <footer>
        <p>© SkyNet - Company 2012</p>
      </footer>

</body>
</html>