package com.wyy.shopping.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wyy.shopping.util.DB;

public class User {
	private int id;
	private String username;
	private String password;
	private String phone;
	private String addr;
	private Date rdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	
	public void save() {
		Connection connection = DB.getConn();
		String sql = "insert into user values(null,?,?,?,?,?) ";
		PreparedStatement pstmt = DB.getPreStmt(connection, sql);
		try {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, phone);
			pstmt.setString(4, addr);
			pstmt.setTimestamp(5, new Timestamp(rdate.getTime()));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closePStmt(pstmt);
			DB.closeConn(connection);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static List<User> getUsers(){
		List<User> users = new ArrayList<User>();
		Connection connection = null;
		String sql = "select * from user";
		Statement statement = DB.getStmt(connection);
		ResultSet rs = DB.getRs(statement, sql);
		try {
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setAddr(rs.getString("addr"));
				user.setRdate(rs.getTimestamp("rdate"));
				users.add(user);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeRs(rs);
			DB.closeStmt(statement);
			DB.closeConn(connection);
		}
		return users;
	}
	
}
