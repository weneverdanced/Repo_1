<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>注册</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/register.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="top-bar">Online University</div>
    <div class="login-container">
        <h3>用户注册</h3>
        <form id="registerForm" action="${pageContext.request.contextPath}/register" method="post">
            <div class="input-container">
                <i class="fas fa-user"></i>
                <input type="text" id="username" name="username" placeholder="用户名" required>
            </div>
            <div class="input-container">
                <i class="fas fa-id-badge"></i>
                <input type="text" id="id" name="id" placeholder="学号/教工号" required>
            </div>
            <div class="input-container">
                <i class="fas fa-envelope"></i>
                <input type="email" id="email" name="email" placeholder="邮箱" required>
            </div>
            <div class="input-container">
                <i class="fas fa-lock"></i>
                <input type="password" id="password" name="password" placeholder="密码" required>
            </div>
            <div class="input-container">
                <i class="fas fa-user-tag"></i>
                <select id="type" name="type" required>
                    <option value="student">学生</option>
                    <option value="teacher">教师</option>
                </select>
            </div>
            <button type="submit" id="registerButton">提交</button>
            <div>
                <button type="reset" id="resetButton">重置</button>
                <button type="button" id="loginButton">登录</button>
            </div>
        </form>
    </div>

    <div id="message-container"
         data-error-message="<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>"
         data-success-message="<%= request.getAttribute("successMessage") != null ? request.getAttribute("successMessage") : "" %>">
    </div>
</body>
</html>
