package com.wby.controller;

import com.wby.entity.Users;
import com.wby.service.AccountService;
import com.wby.service.service_imp.AccountServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private AccountService loginService = new AccountServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        Users user = loginService.login(id, password);

        if (user != null) {
            HttpSession session = req.getSession();
            String type = user.getType();
            switch (type) {
                case "student":
                    session.setAttribute("student", user);
                    req.setAttribute("student", user);
                    req.getRequestDispatcher("student.jsp").forward(req, resp);
                    break;
                case "teacher":
                    session.setAttribute("teacher", user);
                    req.setAttribute("teacher", user);
                    req.getRequestDispatcher("teacher.jsp").forward(req, resp);
                    break;
                default:
                    req.setAttribute("errorMessage", "用户类型无效");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                    break;
            }
        } else {
            req.setAttribute("errorMessage", "用户名或密码错误");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
