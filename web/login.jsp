<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>Login to Engineer Bookstore</title>


<link rel="stylesheet" href="css/style.css">

</head>

<body>

	<div id="top-bar">

		<div class="page-full-width">

			<a href="#" class="round button dark ic-left-arrow image-left ">Return
				to homepage</a>

		</div>


	</div>



	<div id="header">

		<div class="page-full-width cf">

			<div id="login-intro" class="fl">

				<h1>Login to Engineer Bookstore</h1>
				<h5>Enter your username and password below</h5>

			</div>

		</div>


	</div>

	<div id="content">

		<form action="LoginServlet" method="post" id="login-form"
			name="loginForm">

			<fieldset>

				<p>
					<label for="login-username">username</label> <input type="text"
						id="login-username" class="round full-width-input" name="username"
						autofocus />
				</p>

				<p>
					<label for="login-password">password</label> <input type="password"
						id="login-password" class="round full-width-input" name="password" />
				</p>

				<input type="submit" name="submit" value="login"
					class="button round blue image-right ic-right-arrow" />

			</fieldset>

			<br />
			<div class="information-box round">
				<%
					String errorMsg = (String) session.getAttribute("errorMsg");
					if (errorMsg == null) {
				%>
				<strong>Please type user name and password.</strong>
				<%
					} else {
				%>
				${sessionScope.errorMsg}
				<%
					}
				%>
			</div>

		</form>

	</div>



	<div id="footer">

		<p>
			&copy; Copyright 2015 <a href="#">Lab 2 Team 7</a>. All rights
			reserved.
		</p>

	</div>


</body>
</html>
