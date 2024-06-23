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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.wplab.entity.BoardDO;
import com.wplab.entity.UserDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;
import com.wplab.service.BoardDoByBoardDtoConverter;

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
		BoardDoByBoardDtoConverter converter=new BoardDoByBoardDtoConverter();
		HttpSession session=request.getSession(false);
		
		List<BoardDO> list = null;
		
		
	    String viewState = request.getParameter("viewState");
	    

	    if (session != null && session.getAttribute("name") == null) {
            session.setAttribute("name", "guest");
        }	   
	    
        if ("all".equals(viewState) && session != null) {
            session.removeAttribute("searchList");
        }
		
        if(session!=null&& session.getAttribute("searchList")!=null && session.getAttribute("name")=="guest") {
			System.out.println("비회원 검색한 리스트 띄우기");
			//ession.setAttribute("name", null);
			request.setAttribute("boardList", session.getAttribute("searchList"));
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/prevNoticeBoard.jsp");
			view.forward(request, response);
        }
        else if(session!=null && session.getAttribute("name")=="guest") {
			System.out.println("비회원 전체 리스트 띄우기");
			list=dao.findAll().stream().map(converter::convertBoardDTOtoBoardDO).collect(Collectors.toList());
			request.setAttribute("boardList", list);
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/prevNoticeBoard.jsp");
			view.forward(request, response);
        }
        else if(session!=null&& session.getAttribute("searchList")!=null) {
			System.out.println("검색한 리스트 띄우기");
			request.setAttribute("boardList", session.getAttribute("searchList"));
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/noticeBoard.jsp");
			view.forward(request, response);
		}
		else if(session.getAttribute("name").equals("admin")) {
			response.sendRedirect("start");
		}
	
		else {
			System.out.println("리스트 띄우기");
			list=dao.findAll().stream().map(converter::convertBoardDTOtoBoardDO).collect(Collectors.toList());
			request.setAttribute("boardList", list);
			
			RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/noticeBoard.jsp");
			view.forward(request, response);
		}
		
//		if(session==null) {
//			response.sendRedirect(request.getContextPath()+"/start.html");
//			return;
//		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
