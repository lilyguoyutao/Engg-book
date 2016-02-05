/**
 * 
 */
package edu.albany.servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import edu.albany.bean.Products;
import edu.albany.dao.ProductsDao;

/**
 * @author Yu Zhang
 *
 */
public class ViewProductServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ProductsDao pDao = new ProductsDao();
		Integer productId = Integer.parseInt(request.getParameter("productId"));

		Products product = pDao.getProduct(productId);
		request.setAttribute("product", product);
		request.getRequestDispatcher("editProduct.jsp").forward(request,
				response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}
