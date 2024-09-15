<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.wby.entity.Submission" %>
<%@ page import="com.wby.service.SubmissionService" %>
<%@ page import="com.wby.service.service_imp.SubmissionServiceImp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>批改作业</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
    <h2>批改作业</h2>
    <%
        String studentId = request.getParameter("studentId");
        int assignmentId = 0;
        Submission submission = null;
        boolean validParameters = true;

        try {
            assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
        } catch (NumberFormatException e) {
            validParameters = false;
            out.println("<p>无效的作业ID</p>");
        }

        if (validParameters) {
            SubmissionService submissionService = new SubmissionServiceImp();
            submission = submissionService.getSubmission(studentId, assignmentId);

            if (submission == null) {
                validParameters = false;
                out.println("<p>未找到提交的作业</p>");
            }
        }
    %>

    <% if (validParameters) { %>
        <form id="gradeForm">
            <input type="hidden" name="studentId" value="<%= studentId %>">
            <input type="hidden" name="assignmentId" value="<%= assignmentId %>">
            <div class="form-group">
                <label for="content">作业内容:</label>
                <textarea id="content" name="content" readonly><%= submission.getContent() %></textarea>
            </div>
            <div class="form-group">
                <label for="feedback">反馈:</label>
                <textarea id="feedback" name="feedback"><%= submission.getFeedback() %></textarea>
            </div>
            <div class="form-group">
                <label for="grade">评分:</label>
                <input type="number" id="grade" name="grade" value="<%= submission.getGrade() %>">
            </div>
            <button type="submit" id="submit-button">提交批改</button>
        </form>
    <% } %>

    <script type="text/javascript">
        document.addEventListener('DOMContentLoaded', function() {
            var gradeForm = document.getElementById('gradeForm');
            if (gradeForm) {
                gradeForm.addEventListener('submit', function(event) {
                    event.preventDefault();

                    var studentId = document.querySelector('input[name="studentId"]').value;
                    var assignmentId = document.querySelector('input[name="assignmentId"]').value;
                    var feedback = document.getElementById('feedback').value;
                    var grade = document.getElementById('grade').value;

                    if (!assignmentId || isNaN(assignmentId)) {
                        alert('无效的作业ID');
                        return;
                    }

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', '<%= request.getContextPath() %>/gradeSubmission', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                alert('批改提交成功');
                                window.location.href = '<%= request.getContextPath() %>/teacher.jsp';
                            } else {
                                alert('批改提交失败: ' + xhr.responseText);
                            }
                        }
                    };
                    xhr.send('studentId=' + encodeURIComponent(studentId) + '&assignmentId=' + encodeURIComponent(assignmentId) + '&feedback=' + encodeURIComponent(feedback) + '&grade=' + encodeURIComponent(grade));
                });
            }
        });
    </script>
</body>
</html>
