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

import edu.albany.bean.Orders;
import edu.albany.dao.ReviewsDao;

/**
 * @author Yu Zhang
 *
 */
public class ProductReviewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ReviewsDao pDao = new ReviewsDao();
		Integer sellerId = Integer.parseInt(request.getParameter("sellerId"));

		List<Orders> reviewList = pDao.searchProductReviews(sellerId);
		request.setAttribute("reviewList", reviewList);
		request.getRequestDispatcher("productReviews.jsp").forward(request,
				response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
