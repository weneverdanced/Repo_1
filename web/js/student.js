function homeworkSection() {
    $.ajax({
        url: contextPath + '/getAssignments',
        type: 'GET',
        success: function(data) {
            $('#assignmentList').html(data);
            // 重新绑定点击事件，因为新添加的内容不会自动绑定
            $('#assignmentList h4').each(function() {
                var assignmentId = $(this).attr('data-id');
                var assignmentTitle = $(this).text();
                $(this).on('click', function() {
                    window.location.href = contextPath + '/answerAssignment.jsp?assignmentId=' + assignmentId + '&assignmentTitle=' + encodeURIComponent(assignmentTitle);
                });
            });
        },
        error: function() {
            alert('获取作业列表失败');
        }
    });
    document.getElementById('submissionSection').classList.remove('hidden');
    document.getElementById('accountInfo').classList.add('hidden');
    document.getElementById('homeworkFeedbackContainer').classList.add('hidden');
    document.getElementById('questionFormContainerr').classList.add('hidden');
}

function accountSection() {
    var submissionSection = document.getElementById('submissionSection');
    var accountInfo = document.getElementById('accountInfo');
    if (submissionSection && accountInfo) {
        submissionSection.classList.add('hidden');
        accountInfo.classList.remove('hidden');
        document.getElementById('homeworkFeedbackContainer').classList.add('hidden');
        document.getElementById('questionFormContainerr').classList.add('hidden');
    }
}

function feedbackSection() {
    var studentId = document.getElementById('studentId').value;
    $.ajax({
        url: contextPath + '/getFeedback?studentId=' + studentId,
        type: 'GET',
        success: function(data) {
            $('#homeworkFeedbackList').html(data);
        },
        error: function() {
            alert('获取作业反馈失败');
        }
    });
    document.getElementById('accountInfo').classList.add('hidden');
    document.getElementById('submissionSection').classList.add('hidden');
    document.getElementById('questionFormContainer').classList.add('hidden');
    document.getElementById('homeworkFeedbackContainer').classList.remove('hidden');
}

function viewQuestions() {
    var studentId = $('#studentId').val();

    $('#addQuestionForm').on('submit', function(event) {
        event.preventDefault();
        var question = $('#question').val();
        $.ajax({
            url: contextPath + '/addQuestion',
            type: 'POST',
            data: { studentId: studentId, question: question },
            success: function(response) {
                alert('问题提交成功');
                $('#question').val('');
                loadStudentQuestions(studentId); // 重新加载问题列表
            },
            error: function(response) {
                alert('问题提交失败: ' + response.responseText);
            }
        });
    });

    loadStudentQuestions(studentId);
}

function loadStudentQuestions(studentId) {
    $.ajax({
        url: contextPath + '/getQuestions',
        type: 'GET',
        data: { studentId: studentId },
        success: function(data) {
            var questionsList = $('#questionsList');
            questionsList.empty();
            data.forEach(function(question) {
                var listItem = $('<div  id="singleQuestion">');
                listItem.append('<p>问题: ' + question.question + '</p>');
                listItem.append('<p>回答: ' + (question.answer || '暂未回答') + '</p>');
                questionsList.append(listItem);
            });
            $('#questionsContainer').removeClass('hidden');
        },
        error: function() {
            alert('获取问题列表失败');
        }
    });
    document.getElementById('questionFormContainer').classList.remove('hidden');
    document.getElementById('accountInfo').classList.add('hidden');
    document.getElementById('submissionSection').classList.add('hidden');
    document.getElementById('homeworkFeedbackContainer').classList.add('hidden');
}