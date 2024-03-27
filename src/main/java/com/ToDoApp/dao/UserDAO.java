package com.ToDoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.ToDoApp.model.User;

public class UserDAO {

	private DataSource dataSource;

	public UserDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<User> getAllsUsers() throws Exception{
		
		List<User> listUsers = new ArrayList<User>();
		
		Connection Conn = null;
		Statement Stmt = null;
		ResultSet Rs = null;
		
		try {
			
			Conn = dataSource.getConnection();
			String sql = "select * from user";
			Stmt = Conn.createStatement();
			Rs = Stmt.executeQuery(sql);
			
			while(Rs.next()) {
				
				int id = Rs.getInt("id");
				String firstName = Rs.getString("firstname");
				String email = Rs.getString("email");
				String password = Rs.getString("password");
				
				listUsers.add(new User(id,firstName,email,password));		
			}		
			
			return listUsers;
		
		} finally  {
			close(Conn,Stmt,Rs);
		}
		
	}
	
	public User getUserByEmail(String email)throws Exception {
		User user = null;
		
		Connection Conn = null;
		PreparedStatement Stmt = null;
		ResultSet Rs = null;

		try {
			Conn = dataSource.getConnection();
			String sql = "select * from user where email = ?";
			Stmt = Conn.prepareStatement(sql);
			Stmt.setString(1, email);
			Rs = Stmt.executeQuery();
			
			while(Rs.next()) {
				int id = Rs.getInt("id");
				String firstName = Rs.getString("firstname");
				String Email = Rs.getString("email");
				String password = Rs.getString("password");
				user = new User(id,firstName,Email,password);		
			}		
			
		}		
		finally {
			close(Conn, Stmt, Rs);
		}
		return user;
	}

	public void addUser(User user)throws Exception {
		
		Connection Conn = null;
		PreparedStatement Stmt = null;
		ResultSet Rs = null;

		try {
			Conn = dataSource.getConnection();
			String sql = "insert into user (firstname,email,password) values (?,?,?);";
			Stmt = Conn.prepareStatement(sql);
			Stmt.setString(1, user.getFirstName());
			Stmt.setString(2, user.getEmail());
			Stmt.setString(3, user.getPassWord());
			Stmt.execute();
					
			
		}		
		finally {
			close(Conn, Stmt, Rs);
		}
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) {
			
		try {
			if(conn != null)	conn.close();
			if(stmt != null)	stmt.close();
			if(rs   != null)	rs.close();
		}
	
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
