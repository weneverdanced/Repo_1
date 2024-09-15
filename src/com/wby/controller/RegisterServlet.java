package com.wby.controller;

import com.wby.entity.Users;
import com.wby.service.AccountService;
import com.wby.service.service_imp.AccountServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private AccountService registerService = new AccountServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 获取注册表单提交的数据
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        String email = req.getParameter("email");

        // 用户名/id已存在
        if (registerService.isUsernameOrIdExist(username, id)) {
            req.setAttribute("errorMessage", "该用户已注册");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        // 调用注册业务函数
        Users user = registerService.register(id, username, password, type, email);

        // 判断注册是否成功
        if (user != null) {
            // 注册成功
            req.setAttribute("successMessage", "注册成功，请登录");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            // 注册失败
            req.setAttribute("errorMessage", "注册失败，请重试");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
