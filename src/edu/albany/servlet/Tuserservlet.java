package edu.albany.servlet;
/**
 * @author LILI GUO 
 */
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.albany.bean.T_users;
import edu.albany.bean.Users;
import edu.albany.dao.UserDao;


public class Tuserservlet extends HttpServlet {
	private static final long serialVersionUID = 5217291467593774547L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String logined = (String) session.getAttribute("logined");
		if(logined==null)
		logined="";
		List<T_users> T_userlist=null;
		if(logined.trim().equals("true"))
		{UserDao userdi=new UserDao();
		T_userlist=userdi.searchAll_t_Users();}
		System.out.print("lilyguo");
		request.setAttribute("T_userlist", T_userlist);
		request.getRequestDispatcher("approve.jsp").forward(request,
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
