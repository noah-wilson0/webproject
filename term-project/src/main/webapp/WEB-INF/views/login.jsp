<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <style>
        .container {
            max-width: 560px;
            margin: 0 auto;
        }
        .field-error {
            border-color: #dc3545;
            color: #dc3545;
        }
        .button-inline {
            display: inline-block;
            width: 48%;
            margin-right: 2%;
        }
        .button-inline:last-child {
            margin-right: 0;
        }
    </style>
</head>
<body>
<br>
<div class="container">
    <div class="py-1 text-center">
        <h2>로그인</h2>
        <!-- 로그인 메시지 -->
    </div>

    <form action="login" method="post">
        <!-- 전체 오류 메시지 -->
        <div>
            <label for="loginId">로그인 ID</label>
            <input type="text" id="id" name="id" class="form-control">
            <!-- 로그인 ID 오류 메시지 -->
        </div>
        <div>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" class="form-control">
            <!-- 비밀번호 오류 메시지 -->
        </div>
        <hr class="my-4">
        <div>
            <input class="form-control btn btn-primary btn-lg button-inline" type="submit" value="로그인">
        </div>
    </form>
    
    <form action="signup" method="get">
        <div>
            <button class="form-control btn btn-secondary btn-lg button-inline" type="submit">회원가입</button>
        </div>
    </form>
</div> <!-- /container -->
</body>
</html>
