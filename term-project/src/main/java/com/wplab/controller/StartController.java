package com.wplab.controller;

/**
 * 로그인하기 전 시작 페이지
 */

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.wplab.entity.BoardDO;
import com.wplab.entity.UserDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.service.BoardDoByBoardDtoConverter;

@WebServlet("/start")
public class StartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StartController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dbcpResourceName = super.getServletContext().getInitParameter("dbcp_resource_name");
		BoardDAOImpl dao=new BoardDAObyDBCP(dbcpResourceName);
		HttpSession session=request.getSession();
		BoardDoByBoardDtoConverter converter=new BoardDoByBoardDtoConverter();
		List<BoardDO> list = null;

		list=dao.findAll().stream().map(converter::convertBoardDTOtoBoardDO).collect(Collectors.toList());
		UserDO userDO=new UserDO();
		userDO.setAuthority("manager");
		request.setAttribute("boardList", list);
		//session.setAttribute("name", "user");
		session.setAttribute("user", userDO);
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/prevNoticeBoard.jsp");
		view.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
