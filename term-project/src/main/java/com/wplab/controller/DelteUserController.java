package com.wplab.controller;

import java.io.IOException;

import com.wplab.entity.UserDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;
import com.wplab.repository.UserDAOImpl;
import com.wplab.repository.UserDAObyDBCP;
import com.wplab.repository.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/deleteUser")
public class DelteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DelteUserController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		UserDAOImpl dao=new UserDAObyDBCP(dbcpResourceName);
		BoardDAOImpl boardDAO=new BoardDAObyDBCP(dbcpResourceName);
		HttpSession session=request.getSession(false);
		
		UserDO userDO=(UserDO) session.getAttribute("user");
		String id=userDO.getId();
		
		UserDTO userDTO=new UserDTO();
		userDTO.setId(id);
		userDTO.setName((String) session.getAttribute("name"));
		
		System.out.print("id:"+id);
		System.out.print("name:"+session.getAttribute("name"));
		
		
		dao.delete(userDTO);
		boardDAO.deleteMathWriterAll(userDTO);
		
		response.sendRedirect("start");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
