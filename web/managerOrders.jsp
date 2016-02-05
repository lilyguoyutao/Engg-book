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

<title>Engineer Bookstore</title>

<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet'>
<link rel="stylesheet" href="css/style.css">

<script type="text/javascript">
function submitCloseOrder(orderId) {
	document.CloseOrderServlet.orderId.value = orderId;
	document.CloseOrderServlet.submit();
}

function searchForm() {
	document.ManagerOrdersServlet.submit();
}

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
	function submitinboxservlet() {
		document.Inboxservlet.submit();
	}
	function submitOutboxservlet() {
		document.Outboxservlet.submit();
	}
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
					if ((session.getAttribute("logined") == "false") || (logined == null)) {
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

					<li><a>View Order</a></li>
                    <li><a href="sendnotification.jsp">Send
							notification</a></li>
					<form action="Managersearchusers" method="POST"
						name="Managersearchusers">
						<li><a href="javascript:submitManagersearchusers();">View
								users</a></li>
                    </form>
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

			<form action="CloseOrderServlet" method="POST"
				name="CloseOrderServlet">
				<div class="side-content fr">
					<div class="content-module">

						<div class="content-module-heading cf">

							<h3 class="fl">Order Search</h3>

						</div>

						<div class="content-module-main">
							<table>
								<thead>
									<tr>
										<th>Order Id</th>
										<th>Product Name</th>
										<th>Buyer Name</th>
										<th>Quantity</th>
										<th>Price</th>
										<th>Order Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="orders" items="${ordersList}">
										<tr>
											<td>${orders.orderId}</td>
											<td>${orders.productName}</td>
											<td>${orders.buyerName}</td>
											<td>${orders.quantity}</td>
											<td>${orders.price}</td>
											<td><c:choose>
													<c:when test="${orders.orderStatus==1}">
											open
											<a class="table-actions-button ic-table-edit"
															href="javascript:submitCloseOrder(${orders.orderId});"
															title="Close Order"></a>
													</c:when>
													<c:when test="${orders.orderStatus==2}">
											shipped
											<a class="table-actions-button ic-table-edit"
															href="javascript:submitCloseOrder(${orders.orderId});"
															title="Close Order"></a>
													</c:when>
													<c:when test="${orders.orderStatus==3}">
											close
										</c:when>
													<c:otherwise>

													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<input type="hidden" name="orderId" value="" />
						</div>
					</div>

				</div>
			</form>

		</div>

	</div>


	<div id="footer">

		<p>
			&copy; Copyright 2015 <a href="#">Lab 2 Team 7</a>. All rights
			reserved.
		</p>

	</div>


</body>
</html>