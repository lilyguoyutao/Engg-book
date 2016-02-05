<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>send your notification</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript">
	// seller
	function submitAddProduct() {
		document.AddProductServlet.submit();
	}
	function submitProductReviews() {
		document.ProductReviewsServlet.submit();
	}
	function submitMyProducts() {
		document.MyProductsServlet.submit();
	}
	function submitSellerOrders() {
		document.SellerOrdersServlet.submit();
	}
	function submitSellerProducts() {
		document.SellerProductsServlet.submit();
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
				if ((logined == "true") && accountType == 2) {
			%>

			<div class="side-menu fl">

				<h3>Seller Menu</h3>
				<ul>

					<form action="AddProductServlet" method="POST"
						name="AddProductServlet">
						<li><a href="javascript:submitAddProduct();">Add Product</a></li>
						<input type="hidden" name="sellerId" value="${userId}" />
					</form>

					<form action="MyProductsServlet" method="POST"
						name="MyProductsServlet">
						<li><a href="javascript:submitMyProducts();">My Products</a></li> <input type="hidden" name="sellerId" value="${userId}" />
					</form>

					<form action="SellerOrdersServlet" method="POST"
						name="SellerOrdersServlet">
						<li><a href="javascript:submitSellerOrders();">View
								Orders</a></li> <input type="hidden" name="sellerId" value="${userId}" />
					</form>

					<form action="ProductReviewsServlet" method="POST"
						name="ProductReviewsServlet">
						<li><a href="javascript:submitProductReviews();">Product
								Reviews</a></li> <input type="hidden" name="sellerId" value="${userId}" />
					</form>

					<form action="SellerProductsServlet" method="POST"
						name="SellerProductsServlet">
						<li><a href="javascript:submitSellerProducts();">Product Search</a></li>
					</form>
					
					<li><a href="sendnotification.jsp">Send
							notification for seller</a></li>
					
					
					<form action="Inboxservlet" method="POST"
						name="Inboxservlet">
						<li><a href="javascript:submitinboxservlet();">Inbox of seller</a></li>
                    </form>
                    
                   
                    
                    <form action="Outboxservlet" method="POST"
						name="Outboxservlet">
						<li><a href="javascript:submitOutboxservlet();">Outbox of seller</a></li>
                    </form>
                    
                     
						
                    
                    
				</ul>

			</div>
			

			<%
				//seller
				} else if ((logined == "true") && accountType == 3) {
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

			<div class="side-content fr">
				<div class="content-module">

					<div class="content-module-heading cf">

						<h3 class="fl">Send notification</h3>
						<%String send=(String)request.getAttribute("send");
String recename=(String)request.getAttribute("recename");
if(recename==null)
	recename="";
if (send==null)
 send="";
  if(send.trim().equals("yes"))
  { %>
      <div id="content">
	  <div class="confirmation-box round">send sucessfully</div>
	  </div>
 <% }  else if(send.trim().equals("no")) {%>
       <div id="content">
	   <div class="confirmation-box round">failed, the receiver name may not exsit</div>
	   </div>
   
   <%} %>

					</div>
					
<form action="Managernotification" method="post" id="formnot">
<p>
<label for="sender"><b>Sender_name</b></label>
<input type="text" class="round full-width-input" name="sender" value="${username}" >
</p>
<p>
<label for="receive"><b>Receiver_name</b></label>
<input type="text" class="round full-width-input" name="receive" value="${recename}">
</p>
<p>
<label for="message"><b>Message</b></label>
<textarea id="message" class="round full-width-textarea"
										name="message"></textarea>

<input type="submit" name="submit" value="send"
					class="button round blue image-right ic-right-arrow" />
</p>
</form>

</div>

</div>
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