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
import java.sql.Date;
import java.util.List;

@WebServlet("/addSubmission")
public class AddSubmissionServlet extends HttpServlet {
    private SubmissionService submissionService = new SubmissionServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String studentId = req.getParameter("studentId");
        int assignmentId = Integer.parseInt(req.getParameter("assignmentId"));
        String content = req.getParameter("content");
        Date submissionDate = new Date(System.currentTimeMillis());

        Submission submission = new Submission();
        submission.setAssignmentId(assignmentId);
        submission.setStudentId(studentId);
        submission.setContent(content);
        submission.setSubmissionDate(submissionDate);

        // 检查是否已经存在相同的组合
        List<Submission> existingSubmissions = submissionService.getSubmissionsByAssignmentId(assignmentId);
        for (Submission existingSubmission : existingSubmissions) {
            if (existingSubmission.getStudentId().equals(studentId)) {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
                resp.getWriter().write("Submission already exists for this assignment and student");
                return;
            }
        }

        boolean isAdded = submissionService.addSubmission(submission);
        if (isAdded) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Submission added successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Failed to add submission");
        }
    }
}
