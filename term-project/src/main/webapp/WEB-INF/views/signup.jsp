<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <style>
        .field-error {
            border-color: #dc3545;
            color: #dc3545;
        }
        input[type="text"], input[type="email"], input[type="password"] {
            border: 1.5px rgb(68, 136, 244) solid;
            width: 100%;
            height: 30px;
            border-radius: 5px;
            padding-left: 10px;
            margin-bottom: 10px;
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
            display: inline-block;
        }
        input[type="submit"]:active, input[type="button"]:active {
            outline: 1.5px rgb(27, 76, 155) solid;
            background-color: rgb(68, 136, 244);
        }
    </style>
</head>
<body>
<div class="container" style="max-width: 600px;">
    <div class="text-center">
        <h2>회원 가입</h2>
    </div>

    <h4 class="mb-3">회원 정보 입력</h4>
    <form action="signup" method="post">
         <div>
            <label for="id"> ID</label>
            <input type="text" id="id" name="id" class="form-control">
            <!-- 로그인 ID 오류 메시지 -->
        </div>
         <div>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" class="form-control">
            <!-- 비밀번호 오류 메시지 -->
        </div>
        <div>
            <label for="name">이름</label>
            <input type="text" id="name" name="name" class="form-control">
            <!-- 이름 오류 메시지 -->
        </div>

        <hr class="my-4">

        <div class="text-center">
            <input class="btn btn-primary btn-lg" type="submit" value="회원 가입">
            <input class="btn btn-secondary btn-lg" type="button" value="돌아가기"  onclick="window.history.go(-1);">
        
        </div>
    </form>
</div> <!-- /container -->
</body>
</html>
