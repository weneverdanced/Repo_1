package com.wby.dao.dao_imp;

import com.wby.dao.AssignmentDao;
import com.wby.entity.Assignment;
import com.wby.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssignmentDaoImp implements AssignmentDao {
    private static final String SQL_ADD_ASSIGNMENT = "INSERT INTO assignments (teacher_id, title, description, due_date) VALUES (?, ?, ?, ?)";
    private static final String SQL_GET_ASSIGNMENTS_BY_TEACHER_ID = "SELECT * FROM assignments WHERE teacher_id = ?";

    private static final String SQL_GET_ALL_ASSIGNMENTS = "SELECT * FROM assignments";
    @Override
public boolean addAssignment(Assignment assignment) {
    Connection conn = JDBCUtils.getConnection();
    PreparedStatement preparedStatement = null;

    try {
        int newAssignmentId = getMaxAssignmentId() + 1;
        assignment.setAssignmentId(newAssignmentId);

        preparedStatement = conn.prepareStatement("INSERT INTO assignments (assignment_id, teacher_id, title, description, due_date) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, assignment.getAssignmentId());
        preparedStatement.setString(2, assignment.getTeacherId());
        preparedStatement.setString(3, assignment.getTitle());
        preparedStatement.setString(4, assignment.getDescription());
        preparedStatement.setDate(5, assignment.getDueDate());

        int rows = preparedStatement.executeUpdate();
        return rows > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    } finally {
        JDBCUtils.close(conn, preparedStatement, null);
    }
}


    @Override
    public List<Assignment> getAssignmentsByTeacherId(String teacherId) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Assignment> assignments = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement(SQL_GET_ASSIGNMENTS_BY_TEACHER_ID);
            preparedStatement.setString(1, teacherId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentId(resultSet.getInt("assignment_id"));
                assignment.setTeacherId(resultSet.getString("teacher_id"));
                assignment.setTitle(resultSet.getString("title"));
                assignment.setDescription(resultSet.getString("description"));
                assignment.setDueDate(resultSet.getDate("due_date"));
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }

        return assignments;
    }

    @Override
    public int getMaxAssignmentId() {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int maxId = 0;

        try {
            preparedStatement = conn.prepareStatement("SELECT MAX(assignment_id) AS max_id FROM assignments");
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                maxId = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }

        return maxId;
    }

    @Override
    public List<Assignment> getAllAssignments() {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Assignment> assignments = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement(SQL_GET_ALL_ASSIGNMENTS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Assignment assignment = new Assignment();
                assignment.setAssignmentId(resultSet.getInt("assignment_id"));
                assignment.setTeacherId(resultSet.getString("teacher_id"));
                assignment.setTitle(resultSet.getString("title"));
                assignment.setDescription(resultSet.getString("description"));
                assignment.setDueDate(resultSet.getDate("due_date"));
                assignments.add(assignment);
                System.out.println("查到了作业");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }

        return assignments;
    }
}
