package com.wyy.shopping.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.wyy.shopping.util.DB;

public class Category {
	/**
	 * 最多三个级别
	 */
	public static final int MAX_GRADE = 3;
	/**
	 * 每个级别用两位数字表示
	 */
	public static final int LEVEL_LENGTH = 2;
	
	private int id;

	private String name;

	private String descr;

	private int pid;

	private int cno;

	private int grade;

	

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void update() {
		Connection conn = DB.getConn();
		String sql = "update category set name = ? , descr = ? where id = ?";
		PreparedStatement pstmt = DB.getPreStmt(conn, sql);
		try {
			pstmt.setString(1, name);
			pstmt.setString(2, descr);
			pstmt.setInt(3, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closePStmt(pstmt);
			DB.closeConn(conn);
		}
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}
	
	public List<Category> getChilds() {
		return null;
	}
}
