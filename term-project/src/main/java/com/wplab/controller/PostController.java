package com.wplab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.wplab.entity.BoardDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;


@WebServlet("/PostServlet")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PostController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title=request.getParameter("title");
		
		 if (title == null || title.trim().isEmpty()) {
	            response.sendRedirect("list");
	        }
			String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
			BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
			
			BoardDTO boardDTO=dao.findByBoard(title);
			BoardDO boardDO=new BoardDO(boardDTO);
			
			request.setAttribute("post", boardDO);
			
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/post.jsp");
			view.forward(request, response);
	}
	
	/**
	 * 삭제 수정화면 안넘어감
	 * 수정화면은 따로 만들어야할듯
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		String action = request.getParameter("action");
		
	    String title=request.getParameter("title");
	    String writer=request.getParameter("writer");
	    String context=request.getParameter("context");
	    String regdate=request.getParameter("regdate");
	    
	    BoardDTO boardDTO=new BoardDTO();
	    boardDTO.setWriter(writer);
	    boardDTO.setTitle(title);
	    boardDTO.setContent(context);
	    boardDTO.setRegdate(regdate);
	    
	    if ("삭제".equals(action)) {
	    	dao.delete(boardDTO);
			response.sendRedirect("list");
	    }
	    else if ("수정".equals(action)) {
	    	dao.update(boardDTO);
	    	BoardDO boardDO=new BoardDO(boardDTO);
			request.setAttribute("post", boardDO);
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/post.jsp");
			view.forward(request, response);
	    }
		
		
	}

}
