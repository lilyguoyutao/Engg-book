/**
 * 
 */
package edu.albany.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.albany.dao.OrdersDao;

/**
 * @author Yu Zhang
 *
 */
public class CloseOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		OrdersDao oDao = new OrdersDao();
		int orderId = Integer.parseInt(request.getParameter("orderId"));

		oDao.closeOrder(orderId);
		request.getRequestDispatcher("ManagerOrdersServlet").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
