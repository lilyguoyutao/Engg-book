/**
 * 
 */
package edu.albany.utils;

import java.io.*;
import java.sql.*;

/**
 * @author Yu Zhang
 * 
 */
public class DBConn {

	public static String driver;
	public static String url;
	public static String user;
	public static String password;
	public static Connection conn;
	public static Statement stmt;
	public ResultSet rs;
	static {
		try {
			driver = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://localhost:3306/518_team_db";
			user = "root";
			password = "gllgyt637195";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException classnotfoundexception) {
			classnotfoundexception.printStackTrace();
			System.err.println("db: " + classnotfoundexception.getMessage());
		} catch (SQLException sqlexception) {
			System.err.println("db.getconn(): " + sqlexception.getMessage());
		}
	}

	public DBConn() {
		this.conn = this.getConn();
	}

	public Connection getConn() {
		return this.conn;
	}

	public int doInsert(String sql) {
		int keyValue = -1;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				keyValue = rs.getInt(1);
			}
		} catch (SQLException sqlexception) {
			System.err.println("db.executeInset:" + sqlexception.getMessage());
		} finally {

		}
		return keyValue;
	}

	public void doDelete(String sql) {
		try {
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException sqlexception) {
			System.err.println("db.executeDelete:" + sqlexception.getMessage());
		}
	}

	public int doUpdate(String sql) {
		try {
			stmt = conn.createStatement();
			int i = stmt.executeUpdate(sql);
		} catch (SQLException sqlexception) {
			System.err.println("db.executeUpdate:" + sqlexception.getMessage());
		}
		return 0;
	}

	public ResultSet doSelect(String sql) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			stmt = conn.createStatement(
					java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
					java.sql.ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException sqlexception) {
			System.err.println("db.executeQuery: " + sqlexception.getMessage());
		}
		return rs;
	}

	public void close(ResultSet rs) throws SQLException, Exception {

		if (rs != null) {
			rs.close();
			rs = null;
		}

		if (stmt != null) {
			stmt.close();
			stmt = null;
		}

		if (conn != null) {
			conn.close();
			conn = null;
		}
	}

	public void close() throws SQLException, Exception {
		if (stmt != null) {
			stmt.close();
			stmt = null;
		}

		if (conn != null) {
			conn.close();
			conn = null;
		}
	}

}
