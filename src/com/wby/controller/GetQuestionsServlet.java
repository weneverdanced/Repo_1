package com.wby.controller;

import com.google.gson.Gson;
import com.wby.entity.CourseQA;
import com.wby.service.CourseQAService;
import com.wby.service.service_imp.CourseQAServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getQuestions")
public class GetQuestionsServlet extends HttpServlet {
    private CourseQAService courseQAService = new CourseQAServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        String studentId = req.getParameter("studentId");
        List<CourseQA> questions;

        if (studentId != null && !studentId.isEmpty()) {
            questions = courseQAService.getQuestionsByStudentId(studentId);
        } else {
            questions = courseQAService.getAllQuestions();
        }

        Gson gson = new Gson();
        String json = gson.toJson(questions);
        resp.getWriter().write(json);
    }
}
