package com.wby.controller;

import com.wby.entity.CourseQA;
import com.wby.service.CourseQAService;
import com.wby.service.service_imp.CourseQAServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addQuestion")
public class AddQuestionServlet extends HttpServlet {
    private CourseQAService courseQAService = new CourseQAServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String studentId = req.getParameter("studentId");
        String question = req.getParameter("question");

        CourseQA courseQA = new CourseQA();
        courseQA.setStudentId(studentId);
        courseQA.setQuestion(question);

        boolean isAdded = courseQAService.addQuestion(courseQA);
        if (isAdded) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("提问成功");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("提问失败");
        }
    }
}
