package com.wplab.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.wplab.entity.BoardDO;
import com.wplab.entity.UserDO;
import com.wplab.repository.BoardDAOImpl;
import com.wplab.repository.BoardDAObyDBCP;
import com.wplab.repository.BoardDTO;
import com.wplab.repository.FileDAOImpl;
import com.wplab.repository.FileDAObyDBCP;
import com.wplab.repository.FileDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/writeServlet")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 150
)
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
		String writer=request.getParameter("writer");
		
		BoardDTO boardDTO=new BoardDTO();
		boardDTO.setWriter(writer);
		boardDTO.setTitle(title);
		boardDTO.setContent(content);
		boardDTO.setRegdate();
		
		//BoardDO boardDO=new BoardDO(writer, title, content);
		dao.insert(boardDTO);
		
		boardDTO = dao.findByBoard(title);
		Collection<Part> parts = request.getParts();
		int i = 1;
        for(Part file : parts) {
        	if(file.getName().charAt(0)!='f') continue;
        	String originName = file.getSubmittedFileName();
        	if (originName.equals("")) continue;
        	
        	InputStream fis = file.getInputStream();
        	
        	String realPath = request.getServletContext().getRealPath("/upload");
        	
        	// 디렉토리가 존재하지 않으면 생성
            File uploadDir = new File(realPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
        	String filePath = realPath + File.separator + originName;
        	System.out.println(filePath);
        	FileOutputStream fos = new FileOutputStream(filePath);
        	
        	byte[] buf = new byte[1024];
        	int size = 0;
        	while ((size = fis.read(buf)) != -1) {
        		fos.write(buf, 0, size);
        	}
        	
        	FileDAOImpl fdao = new FileDAObyDBCP(dbcpResourceName);
        	
        	FileDTO fdto = new FileDTO();
        	fdto.setBoardId(boardDTO.getBoardId());
        	fdto.setFileId(i++);
        	fdto.setFileName(originName);
        	fdto.setWriter(writer);
        	
        	fdao.insertFile(fdto);
        	
        	fis.close();
        	fos.close();
        }

		response.sendRedirect("list");

	}

}
