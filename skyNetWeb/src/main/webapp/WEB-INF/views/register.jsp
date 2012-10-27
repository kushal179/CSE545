<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="captcha" uri="/WEB-INF/tlds/captcha.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="utf-8">
<title>Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="<c:url value="/resources/bootstrap/css/bootstrap.css" />"
	rel="stylesheet">
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
					<ul class="nav">
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Login</a></li>
						<li class="active"><a href="#contact">Register</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<center>
		<form:form id="register" commandName="registerationVO">

			<table border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr style="height: 250px">
						<td style="width: 550px; background-color: #333333" valign="top"><br>
							<center>
								<table border="0" cellpadding="10" cellspacing="10">
									<tbody>
										<tr>
											<td>
												<table border="0" cellpadding="3" cellspacing="3">
													<tbody>
														<tr>
															<td colspan="2"><div align="center"
																	style="font-size: 22px; color: #ffffff">Register</div>
																<br></td>
														</tr>
														<tr>
															<td style="color: white">First Name</td>
															<td><form:input path="firstName" /><br /> <font
																color="red"><form:errors path="firstName" /></font></td>
														</tr>
														<tr>
															<td style="color: white">Last Name</td>
															<td><form:input path="lastName" /><br /> <font
																color="red"><form:errors path="lastName" /></font></td>
														</tr>
														<tr>
															<td style="color: white">Email</td>
															<td><form:input path="email" /><br /> <font
																color="red"><form:errors path="email" /></font></td>
														</tr>
														<tr>
															<td style="color: white">Username</td>
															<td><form:input path="userName" /><br /> <font
																color="red"><form:errors path="userName" /></font></td>
														</tr>
														<tr>
															<td style="color: white">Password</td>
															<td><form:input path="password" /> <br /> <font
																color="red"><form:errors path="password" /></font></td>
														</tr>
														<%-- <tr>
															<td style="color: white">Retype Password:</td>
															<td><form:input path="rePassword" /></td>
														</tr> --%>
														<tr>
															<td style="color: white">Role:</td>
															<td><form:select path="role">
																	<form:options items="${roleList}" />
																</form:select>
														</tr>
														<tr>
															<td style="color: white">Department:</td>
															<td><form:select path="department">
																	<form:options items="${deptList}" />
																</form:select>
														</tr>
														<tr>
															<td></td>
															<td><div align="left">
																	<captcha:captcha themeName="white"
																		publickey="6LdIMtgSAAAAAHEwm2t3BSD4GBsFMKBNko6LBOH6"
																		privatekey="6LdIMtgSAAAAALIW_Ec3pU6m0OUBV2BMLnNOYCVD" />
																	<br /> <font color="red"><form:errors
																			path="captcha" /></font> <br /> <input id="submit"
																		type="submit" value="Submit" /> <br />
																</div>
													</tbody>
												</table> <br>
											</td>
										</tr>
									</tbody>
								</table>
							</center></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</center>

	<br />
	<br />
	<br />
	<hr>
	<footer>
		<p>© SkyNet - Company 2012</p>
	</footer>

</body>
</html>