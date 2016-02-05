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

import edu.albany.bean.Notif;
import edu.albany.dao.NotificationDao;


public class Outboxservlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String logined = (String) session.getAttribute("logined");
		int UsrId=(int) session.getAttribute("userId");
		
		if(logined==null)
		logined="";
		List<Notif> nottlist=null;
		if(logined.trim().equals("true"))
		{NotificationDao notfii=new NotificationDao();
		nottlist=notfii.Selectoutbox((UsrId));}
		
		request.setAttribute("nottlist", nottlist);
		System.out.print("notfication33");
		request.getRequestDispatcher("Outbox.jsp").forward(request,
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
