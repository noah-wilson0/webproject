<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserInfo Manager</title>
</head>
<body>
<div style="max-width: 1000px">
	<div>
           <a style="color:blue; style="display: inline-block; font-weight:bold;">${name}</a>
           <span inline-block; >님 반갑습니다.</span>
           <form action="list"style="display: inline-block; method="get">
               <button  float: right; type="submit">뒤로가기</button>
           </form>
           <!-- <form action="logoutServlet"style="display: inline-block; method="get">
               <button  float: right; type="submit">로그아웃</button>
           </form> -->
       </div>
    <hr>
    </div>
    <div>
        <table>
            <thead>
            <tr>
                <th style="width: 100px; text-align: center">번호</th>
                <th style="width: 150px; text-align: center">아이디</th>
                <th style="width: 150px; text-align: center">이름</th>
                <th style="width: 150px; text-align: center">비밀번호</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${userList}" var="user" varStatus="status">
	            <tr>
	            	<form action="infoManagerServlet" method="post">
		                <td style="width: 100px; text-align: center">${status.count}</td>
						<td style="width: 150px; text-align: center">${user.id} <input type="hidden" name="id" value="${user.id}"> </td>
		                <td style="width: 150px; text-align: center">${user.name} <input type="hidden" name="name" value="${user.name}"> </td>
		                <td style="width: 150px; text-align: center">${user.password} <input type="hidden" name="password" value="${user.password}"></td>
		                <td><button type="submit">삭제하기</button></td>         	
	            	</form>
	             </tr>
            </c:forEach>
            
            </tbody>
        </table>
    </div>
</body>
</html>