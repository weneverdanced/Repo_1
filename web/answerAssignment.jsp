<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>提交作业</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/index.css" type="text/css" rel="stylesheet">
    <script type="text/javascript">
        var contextPath = '<%= request.getContextPath() %>';

        $(document).ready(function() {
            $('#addSubmissionForm').submit(function(event) {
                event.preventDefault();

                var studentId = $('#studentId').val();
                var assignmentId = $('#assignmentId').val();
                var content = $('#content').val();

                var xhr = new XMLHttpRequest();
                xhr.open('POST', contextPath + '/addSubmission', true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function() {
                    if (xhr.readyState === XMLHttpRequest.DONE) {
                        if (xhr.status === 200) {
                            alert('作业提交成功');
                            window.location.href = contextPath + '/student.jsp';
                        } else {
                            alert('作业提交失败: ' + xhr.responseText);
                        }
                    }
                };
                xhr.send('studentId=' + encodeURIComponent(studentId) + '&assignmentId=' + encodeURIComponent(assignmentId) + '&content=' + encodeURIComponent(content));
            });
        });
    </script>
</head>
<body>
    <div>
        <h2>提交作业 - <%= URLDecoder.decode(request.getParameter("assignmentTitle"), "UTF-8") %></h2>
        <form id="addSubmissionForm">
            <input type="hidden" id="studentId" value="${student.id}">
            <input type="hidden" id="assignmentId" value="<%= request.getParameter("assignmentId") %>">
            <div>
                <label for="content">作业内容:</label>
                <textarea id="content" name="content" required></textarea>
            </div>
            <div class="button-container"><button type="submit" id="submit-button">提交</button></div>
        </form>
    </div>
</body>
</html>
