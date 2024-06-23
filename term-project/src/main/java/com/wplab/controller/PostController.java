package com.wplab.controller;
import java.io.IOException;
import java.util.List;

import com.wplab.entity.BoardDO;
import com.wplab.entity.UserDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;
import com.wplab.repository.FileDAO;
import com.wplab.repository.FileDAOImpl;
import com.wplab.repository.FileDAObyDBCP;
import com.wplab.repository.FileDTO;
import com.wplab.service.BoardDoByBoardDtoConverter;

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
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		BoardDoByBoardDtoConverter converter=new BoardDoByBoardDtoConverter();
		HttpSession session = request.getSession(false);
		String title=request.getParameter("title");
		
		if (title == null || title.trim().isEmpty()) {
	           response.sendRedirect("list");
	       }
		 
		UserDO currentUser = (UserDO) session.getAttribute("user");
	 	String currentUserName=(String) session.getAttribute("name");
		BoardDTO boardDTO=dao.findByBoard(title);
		//System.out.println("boardDTO.getTItle():"+boardDTO.getTitle());
		BoardDO boardDO=converter.convertBoardDTOtoBoardDO(boardDTO);
		//System.out.println("boardDO.getTItle():"+boardDO.getTitle());
		
		FileDAO fdao = new FileDAObyDBCP(dbcpResourceName);
		FileDTO file = new FileDTO();
		file.setBoardId(boardDTO.getBoardId());
		List<FileDTO> files = fdao.getFileList(file);
		
		request.setAttribute("post", boardDO);
		request.setAttribute("files", files);
		if (currentUserName == null || currentUserName.isBlank()) {
			currentUserName="guest";
		} 
		
        if(currentUserName.equals(boardDO.getWriter())
        		||currentUser.getAuthority().equals("admin")){
        	
        	RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/post.jsp");
        	view.forward(request, response);
        }
        else {
        	RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/adminPost.jsp");
        	view.forward(request, response);
        }	
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		FileDAOImpl fdao = new FileDAObyDBCP(dbcpResourceName);
		String action = request.getParameter("action");
		
		int boardID=Integer.parseInt(request.getParameter("board_id"));
	    String title=request.getParameter("title");
	    String writer=request.getParameter("writer");
	    String content=request.getParameter("content");
	    String regdate=request.getParameter("regdate");
	    BoardDTO boardDTO=new BoardDTO();
	    boardDTO.setBoardId(boardID);
	    boardDTO.setWriter(writer);
	    boardDTO.setTitle(title);
	    boardDTO.setContent(content);
	    boardDTO.setRegdate(regdate);
	    FileDTO fdto = new FileDTO();
	    fdto.setBoardId(boardID);
	    
	    System.out.println("boardDTO"+String.valueOf(boardDTO.getContent()));
	    System.out.println("boardDTO"+String.valueOf(boardDTO.getWriter()));

	    if ("삭제".equals(action)) {
	    	dao.delete(boardDTO);
	    	fdao.deleteFile(fdto);
			response.sendRedirect("list");	
			return;

			}
	    else if ("수정".equals(action)) {
			BoardDoByBoardDtoConverter converter=new BoardDoByBoardDtoConverter();

	    	BoardDO boardDO=converter.convertBoardDTOtoBoardDO(boardDTO);
	    	System.out.println(String.valueOf(boardDO));
			request.setAttribute("post", boardDO);
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/postUpdate.jsp");
			view.forward(request, response);
	    }
		
		
	}

}
