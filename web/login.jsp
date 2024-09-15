<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/login.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="top-bar">Online University</div>
    <div class="login-container">
        <h3>用户登录</h3>
        <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
            <div class="input-container">
                <i class="fas fa-user"></i>
                <input type="text" id="id" name="id" placeholder="学号" required>
            </div>
            <div class="input-container">
                <i class="fas fa-lock"></i>
                <input type="password" id="password" name="password" placeholder="密码" required>
                <i id="togglePassword"></i>
            </div>
            <button type="submit" id="loginButton">登录</button>
            <button type="button" id="signupButton">注册</button>
        </form>
        <div id="userTypeContainer">
            <button type="button" id="switchToTeacher">教师端</button>
            <button type="button" id="switchToStudent" style="display:none;">学生端</button>
        </div>
        <div id="error-message" class="error-message"></div>
        <div class="hint">
            如登录、注册遇到问题，请联系客服
        </div>
    </div>

    <div id="error-message-value" data-error-message="<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>"></div>
    <div id="success-message-value" data-success-message="<%= request.getAttribute("successMessage") != null ? request.getAttribute("successMessage") : "" %>"></div>
</body>
</html>
