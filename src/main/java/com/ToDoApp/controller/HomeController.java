package com.ToDoApp.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.ToDoApp.dao.TodolistDAO;
import com.ToDoApp.model.Todolist;
import com.ToDoApp.model.User;


@WebServlet("/Home")
public class HomeController extends HttpServlet {
	
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(); 
			User user = (User)session.getAttribute("user");
			if(user == null) {
				response.sendRedirect(request.getContextPath() +  "/Authentication");
				return;
			}			
			String action = request.getParameter("ACTION");
			if(action == null) action = "LIST";
			
			switch (action) {
			case "LIST":
				listTask(request,response);
				break;
			case "ADD":
				addTask(request,response);
				break;
			case "DELETE":
				deleteTask(request,response);
				break;	
			default:
				break;
			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id")); 
		
		todolistDAO.deleteTask(id);
		
		listTask(request,response);
		
	}

	private void addTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		User user = (User)session.getAttribute("user");
		
		String task =(String) request.getParameter("new-task-input");
		Todolist todolist = new Todolist(user.getUserId(),task,0);
		
		todolistDAO.addTask(todolist);
		
		
		// send to JSP page (view)
		listTask(request,response);
		
	}

	private void listTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
					HttpSession session = request.getSession(); 
					User user = (User)session.getAttribute("user");
					List<Todolist> listTasks = todolistDAO.getAllTasks(user.getUserId());
					
					request.setAttribute("user", user);
					
					// add students to the request
					request.setAttribute("LIST_TASK", listTasks);
					
					// send to JSP page (view)
					RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
					dispatcher.forward(request, response);
				
	}


}
