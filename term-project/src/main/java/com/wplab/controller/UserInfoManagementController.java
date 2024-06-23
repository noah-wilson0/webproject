package com.wplab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.FileDAOImpl;
import com.wplab.repository.FileDAObyDBCP;
import com.wplab.repository.UserDAOImpl;
import com.wplab.repository.UserDAObyDBCP;
import com.wplab.repository.UserDTO;

/**
 * Servlet implementation class UserInfoManagementController
 */
@WebServlet("/infoManagerServlet")
public class UserInfoManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		UserDAOImpl dao=new UserDAObyDBCP(dbcpResourceName);
		List<UserDTO> userList = dao.getUserList();
		request.setAttribute("userList", userList);
		request.setAttribute("userListSize", userList.size());
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/userInfoManagement.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String writer = String.format("%s(%s)", name, id);
		
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		UserDAOImpl dao=new UserDAObyDBCP(dbcpResourceName);
		BoardDAOImpl boardDAO=new BoardDAObyDBCP(dbcpResourceName);
		FileDAOImpl fdao = new FileDAObyDBCP(dbcpResourceName);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(id);
		userDTO.setName(writer);
		fdao.deleteMathWriterAll(userDTO);
		boardDAO.deleteMathWriterAll(userDTO);
		dao.delete(userDTO);
		
		response.sendRedirect("infoManagerServlet");
	}

}
