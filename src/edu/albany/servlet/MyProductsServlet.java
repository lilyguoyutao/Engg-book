/**
 * 
 */
package edu.albany.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.albany.bean.Products;
import edu.albany.dao.ProductsDao;

/**
 * @author Yu Zhang
 *
 */
public class MyProductsServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ProductsDao sDao = new ProductsDao();
		Integer sellerId = Integer.parseInt(request.getParameter("sellerId"));
		List<Products> productsList = null;
		if ((request.getParameter("productName") != null)
				|| (request.getParameter("productName") != null)) {
			request.setAttribute("productName",
					request.getParameter("productName"));
			request.setAttribute("depName", request.getParameter("depName"));
			productsList = sDao.searchSellerProducts(sellerId,
					request.getParameter("productName"),
					request.getParameter("depName"));
		} else {
			productsList = sDao.searchAllSellerProducts(sellerId);
		}
		request.setAttribute("productsList", productsList);
		request.getRequestDispatcher("myProducts.jsp").forward(request,
				response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}
