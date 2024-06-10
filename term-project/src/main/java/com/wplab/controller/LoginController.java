package com.wplab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.UserDAOImpl;
import com.wplab.repository.UserDAObyDBCP;
import com.wplab.repository.UserDTO;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/login.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		UserDAOImpl dao=new UserDAObyDBCP(dbcpResourceName);
		
		UserDTO userDTO=new UserDTO();
		userDTO.setId(id);
		userDTO.setPassword(password);
		
		UserDTO findedUserDTO=new UserDTO();
		
		findedUserDTO=dao.findUser(userDTO);
		HttpSession session=request.getSession();
		if(session==null) {
			response.sendRedirect("login");
		}
		if(findedUserDTO==null) {
			response.sendRedirect("login");
		}
		else {
			request.setAttribute("id", findedUserDTO.getId());
			request.setAttribute("name", findedUserDTO.getName());
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/list.jsp");
			view.forward(request, response);
		}
	}

}
