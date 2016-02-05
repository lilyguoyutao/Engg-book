package edu.albany.dao;
/**
 * @author LILI GUO 
 */
import java.sql.PreparedStatement;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.albany.bean.Notif;
import edu.albany.bean.Users;
import edu.albany.utils.DBConn;

public class NotificationDao {
	
	private String sendsql;
	DBConn dab=new DBConn();
	public void Insert(int sender_id, int rece_id, String content)
	{ SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time=df.format(new Date());
	  
		sendsql="INSERT INTO notification (sender_id, rece_id, content, Datetim,isread) VALUES("+sender_id+","+rece_id+",'"+content+"',"+"'"
				+time+"',"+1+")";
		
		dab.doInsert(sendsql);}
	public List<Notif> Selectinbox(int UsrId)
	{
		List<Notif> lists = new ArrayList<Notif>();
		String sql = "SELECT * FROM notification WHERE rece_id="+UsrId+" order by Datetim desc";
		try {
			
			ResultSet rs= dab.doSelect(sql);
			System.out.println("print sqldd");
			UserDao usrs=new UserDao();
			while (rs.next()) {
				Notif not=new Notif();
				not.setnoti_id(rs.getInt("noti_id"));
				System.out.println(rs.getInt("noti_id"));
				not.setsend_id(rs.getInt("sender_id"));
				not.setrece_id(rs.getInt("rece_id"));
				not.setsender_name(usrs.findusername(rs.getInt("sender_id")));
				not.setrece_name(usrs.findusername(rs.getInt("rece_id")));
				not.setcontent(rs.getString("content"));
				not.setDatetime(rs.getString("Datetim"));
				System.out.print(rs.getString("Datetim"));
				not.setisRead(rs.getInt("isread"));
				
				lists.add(not);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lists;
	}
	
	public List<Notif> Selectoutbox(int UsrId)
	{
		List<Notif> lists = new ArrayList<Notif>();
		String sql = "SELECT * FROM notification WHERE sender_id="+UsrId+" order by Datetim desc";
		try {
			
			ResultSet rs= dab.doSelect(sql);
			System.out.println("print sqldddd");
			UserDao usrss=new UserDao();
			while (rs.next()) {
				Notif not=new Notif();
				not.setnoti_id(rs.getInt("noti_id"));
				System.out.println(rs.getInt("noti_id"));
				not.setsend_id(rs.getInt("sender_id"));
				not.setrece_id(rs.getInt("rece_id"));
				not.setsender_name(usrss.findusername(rs.getInt("sender_id")));
				not.setrece_name(usrss.findusername(rs.getInt("rece_id")));
				not.setcontent(rs.getString("content"));
				not.setDatetime(rs.getString("Datetim"));
				System.out.print(rs.getString("Datetim"));
				
				not.setisRead(rs.getInt("isread"));
				
				lists.add(not);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lists;
	}
	public int updateread(int noti_id)
	{sendsql="UPDATE notification SET `isread`='2' WHERE `noti_id`="+noti_id;
	  
	 return dab.doUpdate(sendsql);
	}
		
		}


