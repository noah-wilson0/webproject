package com.wplab.controller;

import java.io.IOException;

import com.wplab.entity.BoardDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * 		String writer="tester1";
 		회원 페이지 만들면 바꾸기
 * 
 */
@WebServlet("/writeServlet")
public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username=(String) request.getAttribute("username");
		request.setAttribute("username", username);
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/write.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		request.setCharacterEncoding("UTF-8");
		
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		
		HttpSession session= request.getSession(false);
		//String writer=(String) session.getAttribute("username");
		String writer="tester1";
		BoardDTO boardDTO=new BoardDTO();
		boardDTO.setWriter(writer);
		boardDTO.setTitle(title);
		boardDTO.setContent(content);
		boardDTO.setRegdate();
		
		//ArrayList<BoardDO> boardList=(ArrayList<BoardDO>) session.getAttribute("board");
//		if(boardList==null) {
//			boardList=new ArrayList<BoardDO>();
//		}
		BoardDO boardDO=new BoardDO(writer, title, content);
		//boardList.add(boardDO);
		dao.insert(boardDTO);
		//session.setAttribute("board",boardList);
		response.sendRedirect("list");

		
	}

}
