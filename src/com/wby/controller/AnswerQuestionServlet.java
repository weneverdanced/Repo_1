package com.wby.controller;

import com.wby.service.CourseQAService;
import com.wby.service.service_imp.CourseQAServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/answerQuestion")
public class AnswerQuestionServlet extends HttpServlet {
    private CourseQAService courseQAService = new CourseQAServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        String answerText = req.getParameter("answer");
        String teacherId = req.getParameter("teacherId");
        System.out.println("servlet:"+answerText);
        boolean isAnswered = courseQAService.answerQuestion(questionId, answerText, teacherId);
        if (isAnswered) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("问题回答成功");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("问题回答失败");
        }
    }
}
