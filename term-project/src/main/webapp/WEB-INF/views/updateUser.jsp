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
    <script>
        function togglePassword() {
            var passwordField = document.getElementById("password");
            if (passwordField.type === "password") {
                passwordField.type = "text";
            } else {
                passwordField.type = "password";
            }
        }
    </script>
</head>
<body>
<div class="container" style="max-width: 600px;">
    <div class="text-center">
        <h2>회원 정보 수정</h2>
    </div>

    <h4 class="mb-3">회원 정보</h4>
    <form action="executeUpdateUser" method="post">
         <div>
            <label for="id">ID</label>
            <input type="text" id="id" name="id" class="form-control" value="${requestScope.id}"readonly >
        	<p style="color: #6c757d; font-size: 14px; margin-top: 5px;">ID는 수정할 수 없습니다.</p>
        </div>
         <div>
            <label for="password">비밀번호</label>
            <input type="text" id="password" name="password" class="form-control" value="${requestScope.password}" >
        </div>
        <div>
            <label for="name">이름</label>
            <input type="text" id="name" name="name" class="form-control" value="${requestScope.name}" >
        </div>

        <hr class="my-4">
        <div style="text-align: center; display: inline-block;">
                <input class="btn btn-primary btn-lg" type="submit" value="수정하기">
            </form>
            
            <input class="btn btn-secondary btn-lg" type="button" value="되돌아가기" onclick="window.history.go(-1);">
        </div>
</div> <!-- /container -->
</body>
</html>
