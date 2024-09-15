package com.wby.controller;

import com.wby.dao.UsersDao;
import com.wby.dao.dao_imp.UsersDaoImp;
import com.wby.service.AccountService;
import com.wby.service.service_imp.AccountServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountServiceImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("resetPassword".equals(action)) {
            resetPassword(request, response);
        }
    }

    private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter("id");
        String newPassword = request.getParameter("newPassword");

        boolean success = accountService.updatePassword(newPassword,userId);
        System.out.println(newPassword);
        HttpSession session = request.getSession();
        if (success) {
            System.out.println("成功");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("密码修改成功。");
            session.setAttribute("newPassword",newPassword);
            request.setAttribute("newPassword",newPassword);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            System.out.println("失败");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("密码修改失败。");
        }
    }

}
