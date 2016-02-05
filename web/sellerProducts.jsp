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
	function submitViewReviews(productId) {
		document.ViewReviewsServlet.productId.value = productId;
		document.ViewReviewsServlet.submit();
	}

	function searchForm() {
		document.SellerProductsServlet.submit();
	}
	function submitSellerOrders() {
		document.SellerOrdersServlet.submit();
	}
	function submitProductReviews() {
		document.ProductReviewsServlet.submit();
	}
	function submitAddProduct() {
		document.AddProductServlet.submit();
	}
	function submitMyProducts() {
		document.MyProductsServlet.submit();
	}
	//notification
	function submitinboxservlet() {
		document.Inboxservlet.submit();
	}
	
	function submitOutboxservlet() {
		document.Outboxservlet.submit();
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

					<li><a>Product Search</a></li>
					
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
				}
			%>

			<div class="side-content fr">
				<div class="content-module">

					<div class="content-module-heading cf">

						<h3 class="fl">Product Search</h3>

					</div>
					<form action="SellerProductsServlet" method="POST"
						name="SellerProductsServlet">
						<div class="content-module-main cf">
							<div class="half-size-column fl">
								<p>
									<label for="simple-input">Product Name</label> <input
										type="text" id="simple-input"
										class="round default-width-input" name="productName"
										value="${productName}" /> <label for="simple-input">Department</label>
									<select name="depName">
										<c:choose>
											<c:when test="${depName=='Electrical Engineer'}">
												<option value="">-- All --</option>
												<option value="Electrical Engineer" selected>Electrical
													Engineer</option>
												<option value="Software Engineer">Software Engineer</option>
											</c:when>
											<c:when test="${depName=='Software Engineer'}">
												<option value="">-- All --</option>
												<option value="Electrical Engineer">Electrical
													Engineer</option>
												<option value="Software Engineer" selected>Software
													Engineer</option>
											</c:when>
											<c:otherwise>
												<option value="" selected>-- All --</option>
												<option value="Electrical Engineer">Electrical
													Engineer</option>
												<option value="Software Engineer">Software Engineer</option>
											</c:otherwise>
										</c:choose>
									</select>
								</p>
							</div>

							<a class="button round blue image-right ic-search text-upper"
								href="javascript:searchForm();">Search</a>
						</div>
					</form>
						<div class="content-module-main">
							<table>
								<thead>
									<tr>
										<th>Product Name</th>
										<th>Description</th>
										<th>Price</th>
										<th>Department Name</th>
										<th>Image</th>
										<th>Delete</th>
										<th>Review</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="product" items="${productsList}">
										<tr>
											<td>${product.productName}</td>
											<td>${product.description}</td>
											<td>${product.price}</td>
											<td>${product.depName}</td>
											<td><c:choose>
													<c:when test="${product.image!=null}">
														<img src="product_images/${product.image}" width="200px" />
													</c:when>
													<c:otherwise>
														<img src="product_images/no-image.jpg" width="200px" />
													</c:otherwise>
												</c:choose></td>
											<td><a class="table-actions-button ic-table-delete"
												href="javascript:submitDeleteProduct(${product.productId});"
												title="Delete Product"></a></td>
											<td><a class="table-actions-button ic-table-view"
												href="javascript:submitViewReviews(${product.productId});"
												title="View Reviews"></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<input type="hidden" name="productId" value="" />
						</div>
					<form action="ViewReviewsServlet" method="POST"
						name="ViewReviewsServlet">
						<input type="hidden" name="productId" value="" />
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