<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>글쓰기</title>
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
<form action="writeServlet" method="post" enctype="multipart/form-data">
<table> 
    <tr><td><h2>글쓰기</h2></td></tr>
    <tr><td class="header">Title</td></tr>
    <tr><td><input type="text" placeholder="제목을 입력하세요" name="title" required></td></tr>
    <tr><td class="header">작성자</td></tr>
    <tr><td><input type="text" name="writer" value="${name}" readonly="readonly"></td></tr>
    <tr><td class="header">Comment</td></tr>
    <tr><td><textarea placeholder="내용을 입력하세요" name="content" required></textarea></td></tr>
    <tr><td class="header">파일</td></tr>
    <tr><td><input type="file" name="fileName1"></td></tr>
    <tr><td><input type="file" name="fileName2"></td></tr>
    <tr><td><input type="file" name="fileName3"></td></tr>
    <tr><td>
        <input type="submit" value="등록" onclick="alert('작성 완료!')">
        <input type="button" value="취소" onclick="history.back()">
    </td></tr>
</table>
</form>
</body>
</html>
