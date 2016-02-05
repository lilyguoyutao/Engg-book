/**
 * 
 */
package edu.albany.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.albany.dao.ProductsDao;

/**
 * @author Yu Zhang
 *
 */
public class DeleteManagerProductServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ProductsDao pDao = new ProductsDao();
		int productId = Integer.parseInt(request.getParameter("productId"));

		pDao.deleteProduct(productId);
		request.getRequestDispatcher("ManagerProductsServlet").forward(request,
				response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

}
