$(document).ready(function() {
    // 点击登录按钮返回登录页面
    $('#loginButton').click(function() {
        window.location.href = 'login.jsp';
    });

    // 获取错误和成功消息
    var errorMessage = $('#message-container').data('error-message');
    var successMessage = $('#message-container').data('success-message');

    // 显示错误信息
    if (errorMessage) {
        alert(errorMessage);
    }

    // 显示成功信息
    if (successMessage) {
        alert(successMessage);
    }
});
