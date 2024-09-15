package com.wby.controller;

import com.wby.entity.Submission;
import com.wby.service.SubmissionService;
import com.wby.service.service_imp.SubmissionServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getFeedback")
public class GetFeedbackServlet extends HttpServlet {
    private SubmissionService submissionService = new SubmissionServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println("调用了servlet层");
        try {
            String studentId = req.getParameter("studentId");
            System.out.println("servlet:"+studentId);
            List<Submission> submissions = submissionService.getSubmissionsByStudentId(studentId);
            req.setAttribute("submissions", submissions);
            PrintWriter out = resp.getWriter();
            for (Submission submission : submissions) {
                out.println("<div id=\"singleSubmission\">");
                out.println("<h4 data-assignment-id=\"" + submission.getAssignmentId() + "\">作业ID: " + submission.getAssignmentId() + "</h4>");
                out.println("<p>提交日期: " + submission.getSubmissionDate() + "</p>");
                out.println("<p>作答内容: " + submission.getContent() + "</p>");
                out.println("<p>反馈: " + (submission.getFeedback() != null ? submission.getFeedback() : "暂无反馈") + "</p>");
                out.println("<p>评分: " + (submission.getGrade() != 0 ? submission.getGrade() : "暂无评分") + "</p>");
                out.println("</div>");
            }
            out.close();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
