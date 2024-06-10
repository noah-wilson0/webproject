<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        .field-error {
            border-color: #dc3545;
            color: #dc3545;
        }
    </style>
</head>
<body>
<div class="container" style="max-width: 600px;">
    <div class="text-center">
        <h2>회원 가입</h2>
    </div>

    <h4 class="mb-3">회원 정보 입력</h4>
    <form action="register" method="post">
        <div>
            <label for="name">이름</label>
            <input type="text" id="name" name="name" class="form-control">
            <!-- 이름 오류 메시지 -->
        </div>
        <div>
            <label for="nickname">닉네임</label>
            <input type="text" id="nickname" name="nickname" class="form-control">
            <!-- 닉네임 오류 메시지 -->
        </div>
        <div>
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" class="form-control">
            <!-- 이메일 오류 메시지 -->
        </div>
        <div>
            <label for="loginId">로그인 ID</label>
            <input type="text" id="loginId" name="loginId" class="form-control">
            <!-- 로그인 ID 오류 메시지 -->
        </div>
        <div>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" class="form-control">
            <!-- 비밀번호 오류 메시지 -->
        </div>
        <div>
            <label for="passwordCheck">비밀번호 확인</label>
            <input type="password" id="passwordCheck" name="passwordCheck" class="form-control">
            <!-- 비밀번호 확인 오류 메시지 -->
        </div>

        <hr class="my-4">

        <div class="row">
            <div class="col">
                <input class="w-100 btn btn-primary btn-lg" type="submit" value="회원 가입">
            </div>
            <div class="col">
                <input class="w-100 btn btn-secondary btn-lg" type="button" value="취소" onclick="location.href='board.html'">
            </div>
        </div>
    </form>
</div> <!-- /container -->
</body>
</html>
