package edu.albany.servlet;
/**
 * @author LILI GUO 
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.albany.dao.ProductsDao;
import edu.albany.dao.UserDao;


public class Deleteuserservlet extends HttpServlet {
	private static final long serialVersionUID = 5217291467593774547L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		UserDao pDao = new UserDao();
		int userID = Integer.parseInt(request.getParameter("userID"));
        System.out.print("begin to delete");
		pDao.deleteUsers(userID);
		request.getRequestDispatcher("Managersearchusers").forward(request,
				response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
