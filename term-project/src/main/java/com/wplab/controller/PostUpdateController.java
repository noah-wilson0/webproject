package com.wplab.controller;
/**
 * 로그인 구현 후 작성자 파라미터 추가하기
 * String writer="tester1";
 		회원 페이지 만들면 바꾸기
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;

@WebServlet("/postUpdate")
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PostUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		String action = request.getParameter("action");
		
		int boardID=Integer.parseInt(request.getParameter("board_id"));
		System.out.println("ActionBoardID:"+boardID);

	    String title=request.getParameter("title");
	    String content=request.getParameter("content");
	    String writer=request.getParameter("writer");
	    BoardDTO boardDTO=new BoardDTO();
	    boardDTO.setBoardId(boardID);
	    boardDTO.setWriter(writer);
	    boardDTO.setTitle(title);
	    boardDTO.setContent(content);
	    boardDTO.setRegdate();
		
		dao.update(boardDTO);
		
		response.sendRedirect("list");
	}

}
