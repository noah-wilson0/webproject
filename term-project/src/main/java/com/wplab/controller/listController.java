package com.wplab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.wplab.entity.BoardDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;

@WebServlet("/list")
public class listController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public listController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		
		//String username=(String) request.getAttribute("username");
		//request.setAttribute("username", username);

		List<BoardDO> list=dao.findAll().stream().map(BoardDO::new).collect(Collectors.toList());
		
//		HttpSession session=request.getSession();
//		if(session==null) {
//			response.sendRedirect(request.getContextPath()+"/start.html");
//			return;
//		}
		request.setAttribute("boardList", list);
		
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/list.jsp");
		view.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
