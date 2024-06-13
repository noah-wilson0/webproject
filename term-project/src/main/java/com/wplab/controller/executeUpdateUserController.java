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

@WebServlet("/executeUpdateUser")
public class executeUpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public executeUpdateUserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		UserDAOImpl dao=new UserDAObyDBCP(dbcpResourceName);
		BoardDAOImpl boardDAO=new BoardDAObyDBCP(dbcpResourceName);
		HttpSession session=request.getSession(false);
		
		UserDTO userDTO=new UserDTO();
		
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String prevName=(String) session.getAttribute("name");
		userDTO.setId(id);
		userDTO.setPassword(password);
		userDTO.setName(name);
		dao.update(userDTO);
		boardDAO.updateWriter(prevName, String.format("%s(%s)",name,id));
		session.setAttribute("name", name);
		response.sendRedirect("list");
		
	}

}
