package com.userdbmanagement.mongodb.operations;

import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;
import com.userdbmanagement.mongodb.dao.UserDAO;
import com.userdbmanagement.mongodb.model.User;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if ((name == null || name.length() == 0) || (email == null || email.length() == 0) || (password == null || password.length() == 0)) {
			request.setAttribute("error", "Please complete all filed");
			RequestDispatcher reqDes = getServletContext().getRequestDispatcher("/users.jsp");
			reqDes.forward(request, response);
//			System.out.println("Please complete all filed");
		} else {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setPassword(password);
			
			MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO CLIENT");
			UserDAO userDAO = new UserDAO(mongo);
			userDAO.createUser(user);
			request.setAttribute("success", "User Added");
			
			List<User> users =  userDAO.readAllUsers();
			request.setAttribute("users", users);
			
			RequestDispatcher reqDes = getServletContext().getRequestDispatcher("/users.jsp");
			reqDes.forward(request, response);
		}

//		doGet(request, response);
	}

}
