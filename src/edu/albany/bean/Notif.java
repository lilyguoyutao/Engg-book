/**
 * @author LILI GUO 
 */
package edu.albany.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notif {
	private int noti_id;
	private int sender_id;
	private int rece_id;
	private String content;
	private String datatime;
	private int isread;
	private String sender_name;
	private String rece_name;
    public int getnoti_id()
    {return noti_id;}
    public int getsender_id()
    {return sender_id;}
    public String getsender_name()
    {return sender_name;}
    public String getrece_name()
    {return rece_name;}
    public int getrece_id()
    {return rece_id;}
    public String getcontent()
    {return content;}
    public String getdatatime()
    {
	return datatime;}
    public int getisread()
    {return isread;}
    public void setnoti_id(int noti_id)
    {this.noti_id=noti_id;}
    public void setsend_id(int sender_id)
    {this.sender_id=sender_id;}
    public void setrece_id(int rece_id)
    {this.rece_id=rece_id;}
    public void setsender_name(String sender_name)
    {this.sender_name=sender_name;}
    public void setrece_name(String rece_name)
    {this.rece_name=rece_name;}
    public void setcontent(String content)
    {this.content=content;}
    public void setDatetime(String datatime)
    {this.datatime=datatime;}
    public void setisRead(int isread)
    {this.isread=isread;}
    
}
