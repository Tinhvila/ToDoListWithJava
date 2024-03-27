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
import com.ToDoApp.dao.UserDAO;
import com.ToDoApp.model.User;

@WebServlet("/Home")
public class HomeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private UserDAO userDAO;
  
	@Resource(name="jdbc/to_do_app")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {			
			userDAO = new UserDAO(dataSource);
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
			if(user == null)
				response.sendRedirect(request.getContextPath() +  "/Authentication");
			else 
				listUser(request, response);
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
					
					List<User> users = userDAO.getAllsUsers();
					
					// add students to the request
					request.setAttribute("USER_LIST", users);
					
					// send to JSP page (view)
					RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
					dispatcher.forward(request, response);
				
	}


}
