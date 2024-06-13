package com.wplab.controller;

import java.io.IOException;

import com.wplab.entity.UserDO;
import com.wplab.repository.UserDAOImpl;
import com.wplab.repository.UserDAObyDBCP;
import com.wplab.repository.UserDTO;
import com.wplab.service.UserDoDtoConverter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/infoServlet")
public class UserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserInfoController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		UserDAOImpl dao=new UserDAObyDBCP(dbcpResourceName);
		HttpSession session=request.getSession(false);
		UserDO currentUserDO=(UserDO)session.getAttribute("user");
		
		UserDTO userDTO=new UserDTO();
		userDTO.setId(currentUserDO.getId());
		
		UserDTO findedUserDTO=dao.findUser(userDTO);
		
		request.setAttribute("doUser", findedUserDTO);
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/userInfo.jsp");
		view.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
