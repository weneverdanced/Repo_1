<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/index.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        var contextPath = '<%= request.getContextPath() %>';

        $(document).ready(function() {
            $('#account-management').on('click', function() {
                accountSection();
            });
            $('#homework-check').on('click', function() {
                homeworkSection();
            });
            $('#feedback-check').on('click',function (){
                feedbackSection();
            });
            $('#course-qa').on('click',function (){
                viewQuestions();
            });
        });
    </script>
</head>
<body>
    <ul>
        <li><a href="#account-management" id="account-management">账号管理</a></li>
        <li><a href="#homework-check" id="homework-check">查看作业</a></li>
        <li><a href="#feedback-check" id="feedback-check">作业反馈</a></li>
        <li><a href="#course-qa" id="course-qa">提问箱</a></li>
        <div class="top-bar">Online University</div>
    </ul>

    <div id="accountInfo" class="hidden">
        <h2>账号管理</h2>
        <div>
            <p>账号：<span id="id">${student.id}</span></p>
            <p>用户名：<span id="username">${student.username}</span></p>
            <p>密码：<span id="password">${student.password}</span></p>
            <p>用户类型：<span id="type">${student.type}</span></p>
            <p>邮箱：<span id="email">${student.email}</span></p>
        </div>
        <div class="button-container">
            <button id="resetPasswordButton">重置密码</button>
            <button id="logout">退出登录</button>
        </div>
    </div>

    <div id="resetPasswordModal" class="hidden">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>重置密码</h2>
            <input type="password" id="newPassword" placeholder="新密码">
            <button id="confirmResetButton">确认重置</button>
        </div>
    </div>

    <div id="submissionSection" class="hidden">
        <input type="hidden" id="studentId" value="${student.id}">
        <h2>查看作业</h2>
        <div id="assignmentList">
            <%-- 作业列表将由AJAX请求加载 --%>
        </div>
    </div>

    <div id="homeworkFeedbackContainer" class="hidden">
        <h2>作业反馈</h2>
        <div id="homeworkFeedbackList"></div>
    </div>

    <div id="questionFormContainer" class="hidden">
        <h2>提问</h2>
        <form id="addQuestionForm">
            <div>
                <label for="question">问题:</label>
                <textarea id="question" name="question" required></textarea>
            </div>
            <div class="button-container"><button type="submit" id="submit-button">提交</button></div>
        </form>

        <div id="questionsContainer">
        <h2>我的提问</h2>
        <div id="questionsList"></div>
    </div>
    </div>

    <script type="text/javascript" src="js/accountOperation.js"></script>
    <script type="text/javascript" src="js/student.js"></script>
</body>
</html>
