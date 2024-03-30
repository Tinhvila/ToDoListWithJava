package com.ToDoApp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ToDoApp.model.Todolist;

public class TodolistDAO {
	
	private DataSource dataSource;

	public TodolistDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Todolist> getAllTasks(int UserID) throws Exception{
		List<Todolist> listTasks = new ArrayList<Todolist>();
			
		Connection Conn = null;
		PreparedStatement Stmt = null;
		ResultSet Rs = null;
		
		try {
				
			Conn = dataSource.getConnection();
			String sql = "select * from todolist where userID = ?";
			Stmt = Conn.prepareStatement(sql);
			Stmt.setInt(1,UserID);
			Rs = Stmt.executeQuery();
			
			while(Rs.next()) {
				
				int id = Rs.getInt("id");
				int userID = Rs.getInt("userID");
				String task = Rs.getString("task");
				int status = Rs.getInt("status");
				
				listTasks.add(new Todolist(id,userID,task,status));		
			}		
			
			return listTasks;
		
		} finally  {
			close(Conn,Stmt,Rs);
		}
	}
	
public void addTask(Todolist t)throws Exception {
		
		Connection Conn = null;
		PreparedStatement Stmt = null;
		ResultSet Rs = null;

		try {
			Conn = dataSource.getConnection();
			String sql = "insert into todolist (userID,task,status) values (?,?,?);";
			Stmt = Conn.prepareStatement(sql);
			Stmt.setInt(1, t.getUserID());
			Stmt.setString(2, t.getTask());
			Stmt.setInt(3, t.getStatus());
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

	public void deleteTask(int id) throws SQLException {

		Connection Conn = null;
		PreparedStatement Stmt = null;
		ResultSet Rs = null;

		try {
			Conn = dataSource.getConnection();
			String sql = "delete from todolist where id = ?;";
			Stmt = Conn.prepareStatement(sql);
			Stmt.setInt(1, id);
			Stmt.execute();	
			
		}		
		finally {
			close(Conn, Stmt, Rs);
		}
		
	}
}
