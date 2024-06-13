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
        <h2>회원 정보</h2>
    </div>

    <h4 class="mb-3">회원 정보</h4>
    <form action="updateUser" method="post">
         <div>
            <label for="id">ID</label>
            <input type="text" id="id" name="id" class="form-control" value="${requestScope.doUser.id}" readonly>
        </div>
         <div>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" class="form-control" value="${requestScope.doUser.password}" readonly>
            <input type="checkbox" onclick="togglePassword()"> 비밀번호 보이기
        </div>
        <div>
            <label for="name">이름</label>
            <input type="text" id="name" name="name" class="form-control" value="${requestScope.doUser.name}" readonly>
        </div>

        <hr class="my-4">
        <div style="text-align: center; display: inline-block;">
                <input class="btn btn-primary btn-lg" type="submit" value="회원 정보 수정" style="width: 150px;">
            </form>
            <form action="deleteUser" method="get" style="display: inline-block;">
                <input class="btn btn-danger btn-lg" type="submit" value="회원 탈퇴">
            </form>
            
                <input class="btn btn-secondary btn-lg" type="button" value="되돌아가기" onclick="window.history.go(-1);">
        </div>
</div> <!-- /container -->
</body>
</html>
