package com.wplab.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.wplab.entity.BoardDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;
import com.wplab.service.FindMathContenter;

@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		String searchCode=request.getParameter("searchCode");
		String searchWord=request.getParameter("searchWord");
		System.out.println("searchCode:"+searchCode);
		System.out.println("searchWord:"+searchWord);
		ArrayList<BoardDTO> boardDTOList = null;
		ArrayList<BoardDO> boardDOList = null;
		HttpSession session=request.getSession(false);
		switch(searchCode) {
	    case "제목":
	    	boardDTOList=dao.findByTitleBoard(searchWord);
	    	if(!boardDTOList.isEmpty()) {
	    		boardDOList=new ArrayList<BoardDO>();
		        for (BoardDTO boardDTO : boardDTOList) {
		            boardDOList.add(new BoardDO(boardDTO));
		        }
	    	}
	        break;
	        
	    case "내용":
	    	 boardDTOList=dao.findAll();
	    	 if(!boardDTOList.isEmpty()) {
		    	boardDOList=new ArrayList<BoardDO>();
		    	FindMathContenter findcontent=new FindMathContenter();
		    	boardDOList=findcontent.getMathContentByWord(boardDTOList, searchWord);
	    	 }
	        break;
	        
	    case "작성자":
	    	boardDTOList=dao.findByWriterBoard(searchWord);
	    	if(!boardDTOList.isEmpty()) {
	    		boardDOList=new ArrayList<BoardDO>();
		        for (BoardDTO boardDTO : boardDTOList) {
		            boardDOList.add(new BoardDO(boardDTO));
		        }
	    	}
	    	break;
	    	
	    default:
	    	boardDOList=null;
	        break;
		}
		if(boardDOList==null) {
			//검색된 게시글이 없으므로 따로 처리  //아니면 검색어가 없다는 페이지라거나 alert 띄우기?
			response.sendRedirect("list");	  
			return;

		}
		else {
			session.setAttribute("searchList", boardDOList);
			response.sendRedirect("list");	  
			return;

		}
		
		
		
	}

}
