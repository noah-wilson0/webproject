<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 조회</title>
<style>
    table {
        margin: auto;
    }
    input[type="text"] {
        border: 1.5px rgb(68, 136, 244) solid;
        width: 500px;
        height: 30px;
        border-radius: 5px;
        padding-left: 10px;
    }
    textarea {
        border: 1.5px rgb(68, 136, 244) solid;
        width: 500px;
        height: 400px;
        border-radius: 5px;
        padding-left: 10px;
        padding-top: 10px;
        resize: none;
    }
    .header {
        height: 30px;
    }
    input[type="submit"], input[type="button"] {
        width: 100px;
        height: 40px;
        font-size: 15px;
        border: 0;
        outline: 1.5px rgb(68, 136, 244) solid;
        border-radius: 5px;
        padding-left: 10px;
        background-color: rgb(164, 199, 255);
        margin-right: 10px;
    }
    input[type="submit"]:active, input[type="button"]:active {
        width: 100px;
        height: 40px;
        font-size: 15px;
        border: 0;
        border-radius: 5px;
        outline: 1.5px rgb(27, 76, 155) solid;
        padding-left: 10px;
        background-color: rgb(68, 136, 244);
    }
</style>
</head>
<body>
    <table> 
        <form action="PostServlet" method="post">
            <tr><td><h2>게시글 조회</h2></td></tr>
            <tr><td class="header">Title</td></tr>
             <tr><td><input type="hidden" name="board_id" value="${post.boardId}"></td></tr>
            <tr><td><input type="text" name="title" value="${post.title}" readonly="readonly"></td></tr>
            <tr><td class="header">작성자</td></tr>
            <tr><td><input type="text" name="writer" value="${post.writer}" readonly="readonly"></td></tr>
            <tr><td class="header">작성일</td></tr>
        <tr><td><input type="text" name="regdate" value="${post.regdate}" readonly="readonly"></textarea></td></tr>
            <tr><td class="header">내용</td></tr>
            <tr><td><textarea name="content" readonly="readonly">${post.content}</textarea></td></tr>
            <tr><td>
                <input type="submit" name="action" value="수정">
                <input type="submit" name="action" value="삭제">
  			</form>
                <form action="list" method="get">
                    <input type="submit" value="목록">
                </form>
            </td></tr>
      
    </table>
</body>
</html>
