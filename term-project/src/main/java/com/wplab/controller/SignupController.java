package com.wplab.controller;
/**
 * 관리자 어드민에 따라 setAuthorityt수정하기
 */
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.wplab.repository.UserDAOImpl;
import com.wplab.repository.UserDAObyDBCP;
import com.wplab.repository.UserDTO;

@WebServlet("/signup")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SignupController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/signup.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		UserDAOImpl dao=new UserDAObyDBCP(dbcpResourceName);
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		//String authority=request.getParameter("authority");
		
		UserDTO userDTO=new UserDTO();
		userDTO.setId(id);
		userDTO.setPassword(password);
		userDTO.setName(name);
		userDTO.setAuthority("admin");
		dao.insert(userDTO);
	
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/login.jsp");
		view.forward(request, response);

			
		
		
		
		
		
		
	}

}
