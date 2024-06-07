<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <div>
            <a style="color:blue; font-weight:bold;">example_user</a>
            <span>님 반갑습니다.</span>
            <form action="infoServlet" method="post">
                <button type="submit">회원 프로필</button>
            </form>
            <form action="logoutServlet" method="post">
                <button type="submit">로그아웃</button>
            </form>
        </div>
    </div>
    <hr>
    <!-- 글쓰기 버튼 -->
    <div>
        <form action="writeServlet" method="get">
            <button type="submit">글쓰기</button>
        </form>
    </div>
    <hr>
    <!-- 게시글 목록 table -->
    <div>
        <table>
            <thead>
            <tr>
                <th style="width: 100px; text-align: center">번호</th>
                <th style="width: 500px; text-align: center">제목</th>
                <th style="width: 150px; text-align: center">작성자</th>
                <th style="width: 150px; text-align: center">등록일</th>
                <th style="width: 100px; text-align: center">조회수</th>
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
                <td style="width: 100px; text-align: center">0</td> <!-- 조회수는 0으로 가정 -->
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
