package com.wyy.shopping.category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.wyy.shopping.util.DB;

public class CategoryService {
	
	private static CategoryService service;
	
	private CategoryService(){
		
	}
	
	public static CategoryService getInstance(){
		if (service == null) {
			service = new CategoryService();
		}
		return service;
	}
	
	public List<Category> getChilds(int id){
		List<Category> categories = new ArrayList<Category>();
		Connection connection = DB.getConn();
		Statement statement = DB.getStmt(connection);
		ResultSet rs = DB.getRs(statement, "select * from category where pid="+id);
		try {
			while (rs.next()) {
				Category category = getCategoryFromRs(rs);
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeConn(connection);
		}
		return categories;
		
	}

	private Category getCategoryFromRs(ResultSet rs) {
		Category category = new Category();
		try {
			category.setId(rs.getInt("id"));
			category.setPid(rs.getInt("pid"));
			category.setName(rs.getString("name"));
			category.setDescr(rs.getString("descr"));
			category.setCno(rs.getInt("cno"));
			category.setGrade(rs.getInt("grade"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return category;
	}
	
	public List<Category> getCategories(){
		List<Category> categories = new ArrayList<Category>();
		Connection conn = DB.getConn();
		String sql = "select * from category order by cno";
		Statement stmt = DB.getStmt(conn);
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Category c = this.getCategoryFromRs(rs);
				categories.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeRs(rs);
			DB.closeStmt(stmt);
			DB.closeConn(conn);
		}
		return categories;
		
	}
	
	

}
