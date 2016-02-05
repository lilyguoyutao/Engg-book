<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>search all the users</title>

<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet'>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
	

	//manager
		function submitManagerProducts() {
		document.ManagerProductsServlet.submit();
	}
	function submitManagerOrders() {
		document.ManagerOrdersServlet.submit();
	}
	function submitManagersearchusers() {
		document.Managersearchusers.submit();
	}
	function submitdeleteuser(userID)
	{
	    var url = "Deleteuserservlet?userID="+userID;
	    location.href=url;}
	
	function searchForm() {
		document.Managersearchusers.submit();
	}
	function submitinboxservlet() {
		document.Inboxservlet.submit();
	}
	function submitOutboxservlet() {
		document.Outboxservlet.submit();
	}
	function submitreply(sendername)
	{
	    var url = "Replyservlet?sendername="+sendername;
	    location.href=url;}
	function submittuserervlet() {
		var url = "Tuserservlet";
	    location.href=url;
	}
	
</script>
</head>
<body>
<div id="top-bar">

		<div class="page-full-width cf">

			<ul id="nav" class="fl">
				<li class="v-sep"><img src="images/logo.png" /></li>
				<%
					String logined = (String) session.getAttribute("logined");
					String username = (String) session.getAttribute("username");
					Integer accountType = (Integer) session.getAttribute("accountType");
					if ((session.getAttribute("logined") == "false")
							|| (logined == null)) {
				%>

				<li class="v-sep"><a href="login.jsp"
					class="round button dark ic-left-arrow image-left">Go to login
						page</a></li>
				<li class="v-sep"><a href="#"
					class="round button dark ic-favorite image-left">Home Page</a></li>
				<%
					} else if (logined == "true") {
				%>
				<li class="v-sep"><a href="#"
					class="round button dark menu-user image-left"> Logged in as <strong>${sessionScope.username}
							Account</strong>
				</a></li>

				<li class="v-sep">
					<form action="LoginServlet" method="post">
						<input type="submit" name="submit" value="logout"
							class="round button dark ic-power image-right" />
					</form>
				</li>

				<%
					}
				%>

			</ul>



		</div>


	</div>



	<div id="content">

		<div class="page-full-width cf">

			<%
				//seller
				if (logined == "true") {
			%>

			<div class="side-menu fl">

				<h3>Manager Menu</h3>
				<ul>

					<form action="ManagerProductsServlet" method="POST"
						name="ManagerProductsServlet">
						<li><a href="javascript:submitManagerProducts();">Product
								Search</a></li>
					</form>

					<form action="ManagerOrdersServlet" method="POST"
						name="ManagerOrdersServlet">
						<li><a href="javascript:submitManagerOrders();">View
								Order</a></li>
					</form>
					
					<li><a href="sendnotification.jsp">Send
							notification</a></li>
					
						<li><a href="javascript:submitManagersearchusers();">View
								users</a></li>
                   <form action="Inboxservlet" method="POST"
						name="Inboxservlet">
						<li><a href="javascript:submitinboxservlet();">Inbox</a></li>
                    </form>
                     <form action="Outboxservlet" method="POST"
						name="Outboxservlet">
						<li><a href="javascript:submitOutboxservlet();">Outbox</a></li>
                    </form>
                    <li><a href="javascript:submittuserervlet();">Approve</a></li>
				</ul>

			</div>

			<%
				}
			%>
	<div class="side-content fr">
					<div class="content-module">

						<div class="content-module-heading cf">

							<h3 class="fl">Users Search</h3>

						</div>
						
                     
						<div class="content-module-main">
	                       
	                       <table>
								<thead>
									<tr>
										<th>User_id</th>
										<th>User_name</th>
										<th>First name</th>
										<th>Last name</th>
										<th>account_type</th>
										<th>sender_notification</th>
										<th>delete</th>
										
										
									</tr>
								</thead>
								<tbody>
									<c:forEach var="usersmanage" items="${userlist}">
										<tr>
                                         <td>${usersmanage.userId}</td>
										 <td>${usersmanage.username}</td>
										 <td>${usersmanage.firstName}</td>
										 <td>${usersmanage.lastName}</td>
										 <td>
										 <c:choose>
													<c:when test="${usersmanage.accountType==1}">
											Buyer
											
													</c:when>
													<c:when test="${usersmanage.accountType==2}">
											Seller
											
													</c:when>
													<c:when test="${usersmanage.accountType==4}">
											Seller
											
													</c:when>
										 
										 </c:choose>
										 
										 
										 </td>
										 <td>
										 <a 
												href="javascript:submitreply('${usersmanage.username}');"
												title="reply">send to</a>
										 
										 
										 </td>
										 
										 <td>
										 
										 
										 
										 <a 
												href="javascript:submitdeleteuser(${usersmanage.userId});"
												title="deletuser">delete</a>
												
												
												
												</td>
												
										</tr>
									</c:forEach>
								</tbody>
							</table>
					<input type="hidden" name="userID" value="" />		
				</div>
				
				</div>
				</div>			
				</div>

	</div>		
</body>
</html>