package com.ToDoApp.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.ToDoApp.dao.TodolistDAO;




/**
 * Servlet implementation class ListOutTaskController
 */
@WebServlet("/ListOutTaskController")
public class ListOutTaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TodolistDAO todolistDAO;
	  
	@Resource(name="jdbc/to_do_app")
	private DataSource dataSource;
	
    

	@Override
	public void init() throws ServletException {
		super.init();
		
		try {			
			todolistDAO = new TodolistDAO(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
