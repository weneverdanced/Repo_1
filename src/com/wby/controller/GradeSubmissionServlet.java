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

@WebServlet("/gradeSubmission")
public class GradeSubmissionServlet extends HttpServlet {
    private SubmissionService submissionService = new SubmissionServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String assignmentIdStr = req.getParameter("assignmentId");
        String studentId = req.getParameter("studentId");
        String feedback = req.getParameter("feedback");
        String gradeStr = req.getParameter("grade");

        if (assignmentIdStr == null || studentId == null || feedback == null || gradeStr == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("缺少必要的参数");
            return;
        }

        int assignmentId;
        int grade;
        try {
            assignmentId = Integer.parseInt(assignmentIdStr);
            grade = Integer.parseInt(gradeStr);
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("无效的作业ID或评分");
            return;
        }

        boolean isUpdated = submissionService.updateSubmission(studentId, assignmentId, feedback, grade);
        if (isUpdated) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("作业反馈更新成功");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("作业反馈更新失败");
        }
    }
}
