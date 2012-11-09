<html lang="en"><head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

    <meta charset="utf-8">
    <title>Change Password</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />" rel="stylesheet">
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
      .errorblock 
      	{
		color: #ff0000;
		background-color: #ffEEEE;
		border: 3px solid #ff0000;
		padding: 8px;
		margin: 16px;
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
              <li><a href="register">Register</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>


	<table border="0" cellpadding="0" cellspacing="0" align="center">
		<tbody>
			<tr style="height: 250px">
				<td style="width: 450px; background-color: #333333" valign="top"
					align="center"><br>
					<table border="0" cellpadding="10" cellspacing="10">
						<tbody>
							<tr>
								<td>
									<table border="0" cellpadding="3" cellspacing="3">
										<tbody>
											<form:form modelAttribute="changePasswordVO" name="my_form"  method="post" onsubmit="return checkForBlanks()">
													
												
												<tr>
													<td colspan="2">
														<div align="left" style="font-size: 22px; color: #ffffff">Change Your Password</div>
														<br>
													<br>
													</td>
												</tr>

												<tr>
												
												<td style="color: white">Old Password:</td>
													<td><form:input path="oldPassword" id="old_password" type="password"/><font
																color="red"><form:errors path="oldPassword" /></font></td>
												</tr>
												<tr>
													<td style="color: white">New Password:</td>
													<td><form:input path="newPassword" id="new_password" type="password"/><font
																color="red"><form:errors path="newPassword" /></font></td>
												</tr>
												<tr>
											
													<td style="color: white">Retype New Password:</td>
													<td><form:input path="rePassword" id="re_new_password" type="password"/><font
																color="red"><form:errors path="rePassword" /></font></td>
												</tr>
												
												
													<td></td>
													<td>
														<div align="left">
														
															<input class="btn btn-primary" type="submit"  value="Submit"  />
														</div>
													</td>
												<tr>
												<td style="color: red">${errorMessage}</td>
												</tr>	
												
													
												<script type="text/javascript">
												function checkForBlanks() {
													
													var data = '${validate}';
													if(data==1)
														{
														alert("Successful");
														
														
														}
													
													
													if(document.getElementById('old_password').value=="")
													{
														alert("Please enter Old Password");
														return false;
													}
													if(document.getElementById('new_password').value=="")
													{
														alert("Please enter New Password");
														return false;
													}
													if(document.getElementById('re_new_password').value=="")
													{
														alert("Please enter Re-Type New Password");
														return false;
													}
												/*	if(document.getElementById('new_password').value!=document.getElementById('re_new_password').value)
													{
														alert("New Password and Re-Type New Password does not match. Please enter correctly.");
														return false;
													}
												*/
													
											       }
										       </script>

											</form:form>
										</tbody>
									</table> <br>
								</td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>


	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
      <hr>
      <footer>
        <p>© SkyNet - Company 2012</p>
      </footer>

</body>
</html>