package com.wby.controller;

import com.wby.entity.Assignment;
import com.wby.service.AssignmentService;
import com.wby.service.service_imp.AssignmentServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addAssignment")
public class AddAssignmentServlet extends HttpServlet {
    private AssignmentService assignmentService = new AssignmentServiceImp();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求和响应的字符编码为UTF-8
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String teacherId = req.getParameter("teacherId");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String dueDateString = req.getParameter("dueDate");

        // 转换字符串到 java.sql.Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date dueDate = null;
        try {
            Date parsedDate = dateFormat.parse(dueDateString);
            dueDate = new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid date format");
            return;
        }

        Assignment assignment = new Assignment();
        assignment.setTeacherId(teacherId);
        assignment.setTitle(title);
        assignment.setDescription(description);
        assignment.setDueDate(dueDate);
        System.out.println("servlet"+title);
        boolean isAdded = assignmentService.addAssignment(assignment);
        if (isAdded) {
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Assignment added successfully");
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Failed to add assignment");
        }
    }
}
