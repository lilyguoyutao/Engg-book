/**
 * 
 */
package edu.albany.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;










import edu.albany.bean.Products;
import edu.albany.bean.T_users;
import edu.albany.bean.Users;
import edu.albany.utils.DBConn;

/**
 * @author Yu Zhang,LILI Guo
 *
 */
public class UserDao {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	DBConn db=new DBConn();

	private void release(ResultSet rs, PreparedStatement pstmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

	public  String findusername(int userID)
	{String sql="SELECt *FROM users WHERE user_id="
				+ userID;
	String use="";
	try {
		pstmt = db.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next()) 
			use= rs.getString("username");
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	release(rs, pstmt);
	     return use;}
	public  int finduserid(String username)
	{String sql="SELECt *FROM users WHERE lower(username)='"
				+ username+"'";
	int usid=-1;
	try {
		pstmt = db.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next()) 
			usid= rs.getInt("user_id");
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	release(rs, pstmt);
	     return usid;}
	public Users loginUser(String username, String password) {

		String sql = "SELECT * FROM users WHERE lower(username)='"
				+ username + "' and password= '" + password + "'";
		
		Users users = new Users();
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				users.setAccountType(rs.getInt("account_type"));
				users.setUserId(rs.getInt("user_id"));
				users.setFirstName(rs.getString("first_name"));
				users.setLastName(rs.getString("last_name"));
				users.setUsername(rs.getString("username"));
				users.setPassword(rs.getString("password"));
			} else {
				users = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return users;
		

	}
	public T_users login_t_User(String username, String password) {

		String sql = "SELECT * FROM T_users WHERE lower(username)='"
				+ username + "' and password= '" + password + "'";
		T_users tusers=new T_users();
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("print tssql");
			
			if (rs.next()) {
				
				tusers.setUserId(rs.getInt("user_id"));
				
				tusers.setUsername(rs.getString("username"));
				tusers.setPassword(rs.getString("password")) ;
				tusers.setFirstName(rs.getString("first_name"));
				tusers.setLastName(rs.getString("last_name"));
				tusers.setAccountType(rs.getInt("account_type"));
				tusers.setapprove(rs.getInt("approve"));
				tusers.setEmail(rs.getString("email"));
				System.out.print(rs.getString("email"));
				tusers.setaddress(rs.getString("address"));
				tusers.setphone(rs.getString("phonenumber"));
				System.out.print(rs.getString("phonenumber"));
				tusers.setbankaccount(rs.getString("bankaccount"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return tusers;
		

	}
	public List<Users> searchAllUsers() {
		List<Users> lists = new ArrayList<Users>();
		String sql = "select * "
				+ "from users " + "where account_type!=3" ;
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("print sql");
			
			while (rs.next()) {
				Users users=new Users();
				users.setUserId(rs.getInt("user_id"));
				System.out.println(rs.getInt("user_id"));
				users.setUsername(rs.getString("username"));
				users.setPassword("****");
				users.setFirstName(rs.getString("first_name"));
				users.setLastName(rs.getString("last_name"));
				users.setAccountType(rs.getInt("account_type"));
				
				lists.add(users);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return lists;
	}
	
	public List<T_users> searchAll_t_Users() {
		List<T_users> lists = new ArrayList<T_users>();
		String sql = "SELECT * FROM t_users;" ;
		try {
			pstmt = db.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("print tssql");
			
			while (rs.next()) {
				T_users tusers=new T_users();
				tusers.setUserId(rs.getInt("user_id"));
				
				tusers.setUsername(rs.getString("username"));
				tusers.setPassword(rs.getString("password")) ;
				tusers.setFirstName(rs.getString("first_name"));
				tusers.setLastName(rs.getString("last_name"));
				tusers.setAccountType(rs.getInt("account_type"));
				tusers.setapprove(rs.getInt("approve"));
				tusers.setEmail(rs.getString("email"));
				System.out.print(rs.getString("email"));
				tusers.setaddress(rs.getString("address"));
				tusers.setphone(rs.getString("phonenumber"));
				System.out.print(rs.getString("phonenumber"));
				tusers.setbankaccount(rs.getString("bankaccount"));
				lists.add(tusers);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		release(rs, pstmt);
		return lists;
	}
	public void deleteUsers(int userID)
	{String sql="DELETE FROM `518_team_db`.`users` WHERE `user_id`="+userID;
	DBConn da=new DBConn();
	da.doDelete(sql);
			 }
	public void update_tusers(int t_userid)
	{String sql="UPDATE `518_team_db`.`t_users` SET `approve`="+2+" WHERE `user_id`="+t_userid;
	db.doUpdate(sql);
	}
	public T_users findtuser(int userID)
	{String sql="SELECt *FROM t_users WHERE user_id="
			+ userID;;
	System.out.println(sql);
	T_users users = new T_users();
	try {
		pstmt = db.conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			users.setAccountType(rs.getInt("account_type"));
			
			users.setFirstName(rs.getString("first_name"));
			users.setLastName(rs.getString("last_name"));
			users.setUsername(rs.getString("username"));
			users.setPassword(rs.getString("password"));
		} else {
			users = null;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	release(rs, pstmt);
	return users;}
	
	
	public void insert_user_from_tuser(T_users t_user)
	{    
	    DBConn da=new DBConn();
		System.out.println("approveyessssss");
		String sql="INSERT INTO users (username, password, first_name, last_name, account_type) VALUES("+"'" + t_user.getUsername()+"','"
		       +t_user.getPassword()+"','"+t_user.getFirstName()+"','"+t_user.getLastName()+"',"+t_user.getAccountType()+")";
		System.out.print(sql);
		da.doInsert(sql);
	}
	public void delete_t_Users(int userID)
	{String sql="DELETE FROM `518_team_db`.`t_users` WHERE `user_id`="+userID;
	DBConn da=new DBConn();
	da.doDelete(sql);
			 }
	    
	}

