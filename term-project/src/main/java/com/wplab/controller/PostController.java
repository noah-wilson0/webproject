package com.wplab.controller;
import java.io.IOException;

import com.wplab.entity.BoardDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;

/**
 * 유저만 삭제,수정되게 코드 수정하기
 */
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


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
			
			//HttpSession session = request.getSession();
	        //String currentUser = (String) session.getAttribute("username");

			request.setAttribute("post", boardDO);
			request.setAttribute("isAuthor",true);
			//request.setAttribute("isAuthor", currentUser != null && currentUser.equals(boardDO.getWriter()));
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/post.jsp");
			view.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		String action = request.getParameter("action");
		
	    String title=request.getParameter("title");
	    String writer=request.getParameter("writer");
	    String content=request.getParameter("content");
	    String regdate=request.getParameter("regdate");
	    BoardDTO boardDTO=new BoardDTO();
	    boardDTO.setWriter(writer);
	    boardDTO.setTitle(title);
	    boardDTO.setContent(content);
	    boardDTO.setRegdate(regdate);

	    if ("삭제".equals(action)) {
	    	dao.delete(boardDTO);
			response.sendRedirect("list");	
			return;

			}
	    else if ("수정".equals(action)) {
	    	BoardDO boardDO=new BoardDO(boardDTO);
			request.setAttribute("post", boardDO);
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/postUpdate.jsp");
			view.forward(request, response);
	    }
		
		
	}

}
