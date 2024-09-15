package com.wby.controller;

import com.wby.entity.Assignment;
import com.wby.service.AssignmentService;
import com.wby.service.service_imp.AssignmentServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAssignments")
public class AssignmentServlet extends HttpServlet {
    private AssignmentService assignmentService = new AssignmentServiceImp();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        req.setAttribute("assignments", assignments);

        // 使用PrintWriter直接将HTML内容写入响应
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        for (Assignment assignment : assignments) {
            out.println("<div id=\"singleAssignment\">");
            out.println("<h4 data-id=\"" + assignment.getAssignmentId() + "\" onclick=\"selectAssignment(" + assignment.getAssignmentId() + ", '" + assignment.getTitle() + "')\">" + assignment.getTitle() + "</h4>");
            out.println("<p>" + "教师id："+assignment.getTeacherId() + "\t\t" + "截止日期："+assignment.getDueDate() + "</p>");
            out.println("<p>" + "作业描述："+assignment.getDescription() + "</p>");
            out.println("</div>");
        }
        out.close();
    }
}
