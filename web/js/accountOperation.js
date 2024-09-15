document.addEventListener('DOMContentLoaded', function () {
    var navItems = document.querySelectorAll('ul li a');
    navItems.forEach(function (item) {
        item.addEventListener('click', function (event) {
            navItems.forEach(function (navItem) {
                navItem.parentElement.classList.remove('active');
                navItem.parentElement.style.backgroundColor = '#333';
            });

            //选中后变色
            item.parentElement.classList.add('active');
            item.parentElement.style.backgroundColor = '#075f85';

            var accountInfo = document.getElementById('accountInfo');
            var assignmentFormContainer = document.getElementById('assignmentFormContainer');

            if (item.getAttribute('href') === '#account-management') {
                accountInfo.classList.remove('hidden');
            } else {
                accountInfo.classList.add('hidden');
            }
        });
    });

    document.getElementById('logout').addEventListener('click', function () {
        window.location.href = 'login.jsp';
    });

    var resetPasswordButton = document.getElementById('resetPasswordButton');
    var resetPasswordModal = document.getElementById('resetPasswordModal');
    var newPasswordInput = document.getElementById('newPassword');
    var confirmResetButton = document.getElementById('confirmResetButton');
    var closeModalButtons = document.querySelectorAll('.close');

    resetPasswordButton.addEventListener('click', function () {
        resetPasswordModal.classList.remove('hidden');
    });

    closeModalButtons.forEach(function (btn) {
        btn.addEventListener('click', function () {
            resetPasswordModal.classList.add('hidden');
        });
    });

    confirmResetButton.addEventListener('click', function () {
        var newPassword = newPasswordInput.value;
        var userId = document.getElementById('id').innerText || document.getElementById('id').textContent;

        if (newPassword.trim() !== '' && userId) {
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'AccountServlet', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xhr.onreadystatechange = function () {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        resetPasswordModal.classList.add('hidden');
                        alert('密码修改成功!');
                        window.location.href = 'login.jsp';
                    } else {
                        alert('密码修改失败: ' + xhr.responseText);
                    }
                }
            };
            xhr.send('action=resetPassword&id=' + encodeURIComponent(userId) + '&newPassword=' + encodeURIComponent(newPassword));
        } else {
            alert('请输入新密码');
        }
    });
});
