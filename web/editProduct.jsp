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
	//seller
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
					<li><a>Add Product</a></li>

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

				</ul>

			</div>

			<%
				}
			%>

			<div class="side-content fr">
				<div class="content-module">

					<div class="content-module-heading cf">

						<h3 class="fl">Edit Product</h3>

					</div>


					<div class="content-module-main">

						<%
							String msg = (String) request.getAttribute("msg");
							if (msg != null) {
						%>
						<div class="confirmation-box round">${msg}</div>
						<%
							}
						%>

						<form action="EditProductServlet" method="post"
							name="EditProductServlet" enctype="multipart/form-data">
							<fieldset>
								<p>
									<label for="product-name">Product Name</label> <input
										id="product-name" class="round full-width-input"
										type="text" name="productName" value="${product.productName}">
								</p>
								<p>
									<label for="price">Price</label> <input id="price"
										class="round full-width-input" type="text" name="price"
										value="${product.price}">
								</p>
								<p>
									<label for="description">Description</label>
									<textarea id="description" class="round full-width-textarea"
										name="description">${product.description}</textarea>
								</p>
								<p>
									<label for="department-name">Department Name</label> <input
										id="department-name" class="round full-width-input"
										type="text" name="departmentName" value="${product.depName}">
								</p>
								<p>
									<label for="image">Image</label> <input type="file"
										name="image" id="image" value="${product.image}" />
								</p>

								<input type="hidden" name="sellerId" value="${product.sellerId}" />
								<input type="hidden" name="productId"
									value="${product.productId}" />

								<div class="stripe-separator"></div>

								<input class="round blue ic-right-arrow" type="submit"
									name="submit" value="Submit">
							</fieldset>
						</form>

					</div>
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