<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.wplab.entity.BoardDO" %>
<%@ page import="java.net.URLEncoder" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Board Service</title>
</head>
<body>
<div style="max-width: 1000px">
    <!-- 로그인, 회원 정보 관련 -->
       <div>
           <a style="color:blue; style="display: inline-block; font-weight:bold;">${name}</a>
           <span inline-block; >님 반갑습니다.</span>
           <c:choose>
	           <c:when test="${user.authority != 'admin'}">
		           <form action="infoServlet"style="display: inline-block; method="get">
		               <button  float: right; type="submit">회원 프로필</button>
		           </form>
	           </c:when>
	           <c:otherwise>
			       <form action="infoManagerServlet"style="display: inline-block; method="get">
		               <button  float: right; type="submit">회원 관리</button>
		           </form>
	           </c:otherwise>
           </c:choose>
           <form action="logoutServlet"style="display: inline-block; method="get">
               <button  float: right; type="submit">로그아웃</button>
           </form>
       </div>
    <hr>
    </div>
    <div>
		<!-- 검색 버튼 -->
		<form action="search" method="post" style="display: inline-block; margin-right: 10px;">
		    <select name="searchCode">
		        <option value="제목">제목</option>
		        <option value="내용">내용</option>
		        <option value="작성자">작성자</option>
		    </select>
		    <input type="text" name="searchWord" class="form-control-sm" style="width: 150px;" placeholder="작성자 입력">
		    <button type="submit">검색</button>
		</form>
		
		<!-- 전체 보기 버튼 -->
		<form action="list" method="get" style="display: inline-block; padding-right: 10px;">
		    <button type="submit">전체 보기</button>
		    <input type="hidden" name="viewState" value="all">
		</form>
		
		<!-- 글쓰기 버튼 -->
		<form action="writeServlet" method="get" style="display: inline-block; float: right; padding-right: 100px;">
		    <button type="submit">글쓰기</button>
		</form>
		<hr>
	</div>

    
    <!-- 게시글 목록 table -->
    <div>
        <table>
            <thead>
            <tr>
                <th style="width: 100px; text-align: center">번호</th>
                <th style="width: 500px; text-align: center">제목</th>
                <th style="width: 150px; text-align: center">작성자</th>
                <th style="width: 150px; text-align: center">등록일</th>
            </tr>
            </thead>
            <tbody>
            <%
                ArrayList<BoardDO> boardList = (ArrayList<BoardDO>) request.getAttribute("boardList");
                if (boardList != null) {
                    for (int i = 0; i < boardList.size(); i++) {
                        BoardDO board = boardList.get(i);
            %>
            <tr>
                <td style="width: 100px; text-align: center"><%= i + 1 %></td>
				<td style="width: 500px; text-align: center">
				    <a href="PostServlet?title=<%= URLEncoder.encode(board.getTitle(), "UTF-8") %>">
				        <%= board.getTitle() %>
				    </a>
				</td>
                <td style="width: 150px; text-align: center"><%= board.getWriter() %></td>
                <td style="width: 150px; text-align: center"><%= board.getRegdate() %></td>
             </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</body>
</html>
