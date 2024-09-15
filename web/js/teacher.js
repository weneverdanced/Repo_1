function showAssignmentForm() {
    document.getElementById('accountInfo').classList.add('hidden');
    document.getElementById('submissionSelection').classList.add('hidden');
    document.getElementById('assignmentFormContainer').classList.remove('hidden');
}

function accountSection(){
    document.getElementById('assignmentFormContainer').classList.add('hidden');
    document.getElementById('submissionSelection').classList.add('hidden');
    document.getElementById('questionsContainer').classList.add('hidden');
    document.getElementById('accountInfo').classList.remove('hidden');
}

function SubmissionCheck(){
    $.ajax({
        url: contextPath + '/getSubmissions',
        type: 'GET',
        success: function(data) {
            $('#submissionList').html(data);
            // 重新绑定点击事件，因为新添加的内容不会自动绑定
            $('#submissionList h4').each(function() {
                var studentId = $(this).attr('data-student-id');
                var assignmentId = $(this).attr('data-assignment-id');
                $(this).on('click', function() {
                    window.location.href = '/gradeSubmission.jsp?studentId=' + studentId + '&assignmentId=' + assignmentId;
                });
            });
        },
        error: function() {
            alert('获取学生作业列表失败');
        }
    });
    document.getElementById('submissionSelection').classList.remove('hidden');
    document.getElementById('assignmentFormContainer').classList.add('hidden');
    document.getElementById('accountInfo').classList.add('hidden');
    document.getElementById('questionsContainer').classList.add('hidden');
}

document.getElementById('addAssignmentForm').addEventListener('submit', function(event) {
    event.preventDefault();
    var teacherId = document.getElementById('teacherId').value;
    var title = document.getElementById('title').value;
    var description = document.getElementById('description').value;
    var dueDate = document.getElementById('dueDate').value;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/addAssignment', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                alert('作业布置成功');
                document.getElementById('assignmentFormContainer').classList.add('hidden');
            } else {
                alert('作业布置失败: ' + xhr.responseText);
            }
        }
    };
    xhr.send('teacherId=' + encodeURIComponent(teacherId) + '&title=' + encodeURIComponent(title) + '&description=' + encodeURIComponent(description) + '&dueDate=' + encodeURIComponent(dueDate));
});

function viewAllQuestions() {
    $.ajax({
        url: contextPath + '/getQuestions',
        type: 'GET',
        success: function(data) {
            var questionsList = $('#questionsList');
            questionsList.empty();
            data.forEach(function(question) {
                var listItem = $('<div>');
                listItem.append('<p>问题: ' + question.question + '</p>');
                listItem.append('<p>问题编号: ' + question.questionId + '</p>');
                listItem.append('<textarea id="answer-' + question.questionId + '">' + (question.answer || '') + '</textarea>');
                listItem.append('<button id="submit-button" onclick="submitAnswer(' + question.questionId + ')">提交回答</button>');
                questionsList.append(listItem);
            });
            $('#questionsContainer').removeClass('hidden');
        },
        error: function() {
            alert('获取问题列表失败');
        }
    });
}

function submitAnswer(questionId) {
    var answer = $('#answer-' + questionId).val();
    var teacherId=document.getElementById('teacher_Id').value;
    $.ajax({
        url: contextPath + '/answerQuestion',
        type: 'POST',
        data: { questionId: questionId, answer: answer ,teacherId:teacherId},
        success: function(response) {
            alert('回答提交成功');
        },
        error: function(response) {
            alert('回答提交失败: ' + response.responseText);
        }
    });
}