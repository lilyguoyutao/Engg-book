package edu.albany.servlet;
/**
 * @author LILI GUO 
 */
import edu.albany.bean.*;
import edu.albany.dao.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Managersearchusers extends HttpServlet {
	private static final long serialVersionUID = 5217291467593774547L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String logined = (String) session.getAttribute("logined");
		if(logined==null)
		logined="";
		List<Users> userlist=null;
		if(logined.trim().equals("true"))
		{UserDao userdi=new UserDao();
		userlist=userdi.searchAllUsers();}
		System.out.print("lilyguo");
		request.setAttribute("userlist", userlist);
		request.getRequestDispatcher("manager_users.jsp").forward(request,
				response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
