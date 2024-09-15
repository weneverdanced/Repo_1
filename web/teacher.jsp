<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>教师页面</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <link href="css/index.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        var contextPath = '<%= request.getContextPath() %>';

        $(document).ready(function() {
            $('#check-submission').on('click', function() {
                SubmissionCheck();
            });
        });
    </script>
</head>
<body>
    <ul>
        <li><a href="#account-management" onclick="accountSection()">账号管理</a></li>
        <li><a href="#submit-homework" onclick="showAssignmentForm()">布置作业</a></li>
        <li><a href="#check-submission" onclick="SubmissionCheck()">批改作业</a></li>
        <li><a href="#course-qa" onclick="viewAllQuestions()">学生答疑</a></li>
        <div class="top-bar">Online University</div>
    </ul>

    <h2>账号管理</h2>
    <div id="accountInfo" class="hidden">
        <div>
            <p>账号：<span id="id">${teacher.id}</span></p>
            <p>用户名：<span id="username">${teacher.username}</span></p>
            <p>密码：<span id="password">${teacher.password}</span></p>
            <p>用户类型：<span id="type">${teacher.type}</span></p>
            <p>邮箱：<span id="email">${teacher.email}</span></p>
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

    <div id="assignmentFormContainer" class="hidden">
        <h2>布置作业</h2>
        <form id="addAssignmentForm">
            <input type="hidden" id="teacherId" value="${teacher.id}">
            <div>
                <label for="title">标题:</label>
                <input type="text" id="title" name="title" required>
            </div>
            <div>
                <label for="description">描述:</label>
                <textarea id="description" name="description" required></textarea>
            </div>
            <div>
                <label for="dueDate">截止日期:</label>
                <input type="date" id="dueDate" name="dueDate" required>
            </div>
            <div class="button-container"><button type="submit" id="submit-button">提交</button></div>
        </form>
    </div>

    <div id="submissionSelection" class="hidden">
        <h2>查看作业</h2>
        <div id="submissionList">
            <%-- 作业列表将由AJAX请求加载 --%>
        </div>
    </div>

     <div id="questionsContainer" class="hidden">
        <h2>回答问题</h2>
         <input type="hidden" id="teacher_Id" value="${teacher.id}">
        <div id="questionsList"></div>
    </div>

    <script type="text/javascript" src="js/teacher.js"></script>
    <script type="text/javascript" src="js/accountOperation.js"></script>
</body>
</html>
