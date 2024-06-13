package com.wplab.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateUser")
public class UpdateUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUserController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("id",request.getParameter("id") );
		request.setAttribute("password",request.getParameter("password") );
		request.setAttribute("name",request.getParameter("name"));
//		System.out.print(request.getParameter("id"));
//		System.out.print(request.getParameter("password"));
//		System.out.print(request.getParameter("name"));
		RequestDispatcher view=request.getRequestDispatcher("WEB-INF/views/updateUser.jsp");
		view.forward(request, response);
	}

}
