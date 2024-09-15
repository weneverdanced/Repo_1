$(document).ready(function() {
    $('#switchToTeacher').click(function() {
        $('#id').attr('placeholder', '教工号');
        $('#switchToTeacher').hide();
        $('#switchToStudent').show();
    });
    $('#switchToStudent').click(function() {
        $('#id').attr('placeholder', '学号');
        $('#switchToTeacher').show();
        $('#switchToStudent').hide();
    });

    // 点击注册按钮跳转到注册页面
    $('#signupButton').click(function() {
        window.location.href = 'register.jsp';
    });

    // 显示错误信息
    var errorMessage = $('#error-message-value').data('error-message');
    if (errorMessage) {
        alert(errorMessage);
    }

    // 显示成功信息
    var successMessage = $('#success-message-value').data('success-message');
    if (successMessage) {
        alert(successMessage);
    }
});
