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

import edu.albany.dao.NotificationDao;
import edu.albany.dao.UserDao;

/**
 * Servlet implementation class Managernotification
 */

public class Managernotification extends HttpServlet {
	private static final long serialVersionUID = 5217291467593774547L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String send="no";
		response.setContentType("text/html;charset=GB2312");
		String submit = request.getParameter("submit");
		String se_name=(request.getParameter("sender"));
		String re_name=(request.getParameter("receive"));
		String se_mes=request.getParameter("message");
		if(submit==null)
		{submit="";}
		if(se_name==null)
		{se_name="";}
		if(re_name==null)
		{re_name="";}
		if(se_mes==null)
		{se_mes="";}
		UserDao us=new UserDao();
		int se_id=us.finduserid(se_name);
		int re_id=us.finduserid(re_name);
		if(se_id>0&&re_id>0)
		{NotificationDao notifa=new NotificationDao();
		notifa.Insert((se_id), (re_id), se_mes);
	     send="yes";}
		request.setAttribute("send",send );
		request.getRequestDispatcher("sendnotification.jsp").forward(request,
				response);
	
	}

}
