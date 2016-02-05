/**
 * 
 */
package edu.albany.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.albany.bean.T_users;
import edu.albany.bean.Users;
import edu.albany.dao.UserDao;

/**
 * @author Yu Zhang
 *
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 5217291467593774547L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String submit = request.getParameter("submit");
		if (submit != null && submit.equals("login")) {

			String username = request.getParameter("username").toLowerCase();
			String password = request.getParameter("password");

			UserDao uDao = new UserDao();

			Users users = uDao.loginUser(username, password);
			T_users tusers=uDao.login_t_User(username, password);

			if (users != null) {
				session.setAttribute("username", username);
				session.setAttribute("accountType", users.getAccountType());
				session.setAttribute("userId", users.getUserId());
				session.setAttribute("logined", "true");
				session.setAttribute("errorMsg", "");
				request.getRequestDispatcher("index.jsp").forward(request,
						response);
			} else if(tusers!=null&&(tusers.getAccountType()==2)&&tusers.getapprove()==1){
				session.setAttribute("username", "");
				session.setAttribute("logined", "false");
				session.setAttribute("errorMsg",
						"");
				session.setAttribute("accountType", 0);
				request.getRequestDispatcher("tem.jsp").forward(request,
						response);
				
			}
			else
			{session.setAttribute("username", "");
			session.setAttribute("logined", "false");
			session.setAttribute("errorMsg",
					"your username and password does not match");
			session.setAttribute("accountType", 0);
			request.getRequestDispatcher("login.jsp").forward(request,
					response);}
		} else if (submit != null && submit.equals("logout")) {
			session.setAttribute("username", "");
			session.setAttribute("logined", "false");
			session.setAttribute("accountType", 0);
			session.setAttribute("userId", 0);
			session.setAttribute("errorMsg",
					"Please type user name and password.");
			try {
				request.getRequestDispatcher("HomePageServlet").forward(
						request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}

	}

}
