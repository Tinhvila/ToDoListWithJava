package com.ToDoApp.controller;

import java.io.IOException;

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


@WebServlet("/Authentication")
public class AuthenController extends HttpServlet {
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

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			if(action == null) action = "AUTHENTICATION";
			
			switch (action) {
			case "AUTHENTICATION":
				authentication(request,response);
				break;
			case "SIGN-UP":
				userSignUp(request,response);
				break;
			case "SIGN-IN":
				userSignIn(request,response);
				break;
			default:
				break;
			}
		}
		catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	private void authentication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/authentication.jsp");
		dispatcher.forward(request, response);
		
	}


	private void userSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email = request.getParameter("emailLogin");
        String password = request.getParameter("passwordLogin");
        
        User user = userDAO.getUserByEmail(email);
        
        if(user == null || !user.getPassWord().equals(password)) {
            request.setAttribute("loginMessage", "*Invalid email or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/authentication.jsp");
            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession(); 
            session.setAttribute("user", user);
            response.sendRedirect("Home");
        }
		
	}

	private void userSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		String email = request.getParameter("email");
        String firstName = request.getParameter("userName");
        String password = request.getParameter("passWord");
        String reTypePw = request.getParameter("reTypePassWord");
        
        if(!password.equals(reTypePw)) {
            request.setAttribute("messageSignUp", "*Passwords do not match");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/authentication.jsp");
            dispatcher.forward(request, response);
        } else {
            User user = userDAO.getUserByEmail(email);
            if(user != null) {
                request.setAttribute("messageSignUp", "*Email has been used");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/authentication.jsp");
                dispatcher.forward(request, response);
            } else {
                userDAO.addUser(new User(firstName, email, password));
                request.setAttribute("messageSignUp", "Sign up successfully");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/authentication.jsp");
                dispatcher.forward(request, response);
            }
        }
}

}
