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
public class SellerProductsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ProductsDao pDao = new ProductsDao();
		List<Products> productsList = null;
		if ((request.getParameter("productName") != null)
				|| (request.getParameter("productName") != null)) {
			request.setAttribute("productName",
					request.getParameter("productName"));
			request.setAttribute("depName", request.getParameter("depName"));
			productsList = pDao.searchManagerProducts(
					request.getParameter("productName"),
					request.getParameter("depName"));
		} else {
			productsList = pDao.searchAllManagerProducts();
		}
		request.setAttribute("productsList", productsList);
		request.getRequestDispatcher("sellerProducts.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
