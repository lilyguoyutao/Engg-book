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
import javax.servlet.http.HttpSession;

import edu.albany.bean.Orders;
import edu.albany.dao.OrdersDao;

/**
 * @author Yu Zhang
 *
 */
public class SellerOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		OrdersDao oDao = new OrdersDao();

		int sellerId = Integer.parseInt(request.getParameter("sellerId"));

		List<Orders> ordersList = oDao.searchSellerOrders(sellerId);
		request.setAttribute("ordersList", ordersList);
		request.getRequestDispatcher("sellerOrders.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
