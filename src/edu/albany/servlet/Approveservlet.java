package edu.albany.servlet;
/**
 * @author LILI GUO 
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.albany.bean.T_users;
import edu.albany.dao.NotificationDao;
import edu.albany.dao.UserDao;

/**
 * Servlet implementation class Approveservlet
 */

public class Approveservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		try{
			int idd = Integer.parseInt((request.getParameter("userID")));
	        System.out.print("begin to read");
			NotificationDao nott=new NotificationDao();
			UserDao us=new UserDao();
			System.out.println("lilyguoyutaoapprove");
			System.out.println(idd);
			us.update_tusers(idd);
			System.out.println("findout");
		    us.insert_user_from_tuser(us.findtuser(idd));
			System.out.println("select succesfull");
			request.getRequestDispatcher("Tuserservlet").forward(request,response);
			}catch(Exception e)
			{e.printStackTrace();}
		}
		
		
		
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
