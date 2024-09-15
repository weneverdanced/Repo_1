package com.wby.dao.dao_imp;

import com.wby.dao.SubmissionDao;
import com.wby.entity.Submission;
import com.wby.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubmissionDaoImp implements SubmissionDao {
    private static final String SQL_ADD_SUBMISSION = "INSERT INTO submissions (assignment_id, student_id, content, submission_date) VALUES (?, ?, ?, ?)";
    private static final String SQL_GET_ALL_SUBMISSIONS = "SELECT * FROM submissions";
    private static final String SQL_GET_SUBMISSIONS_BY_ASSIGNMENT_ID = "SELECT * FROM submissions WHERE assignment_id = ?";
    private static final String SQL_GET_SUBMISSION = "SELECT * FROM submissions WHERE assignment_id = ? AND student_id = ?";
    private static final String SQL_UPDATE_SUBMISSION = "UPDATE submissions SET feedback = ?, grade = ? WHERE assignment_id = ? AND student_id = ?";

    @Override
    public boolean addSubmission(Submission submission) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(SQL_ADD_SUBMISSION);
            preparedStatement.setInt(1, submission.getAssignmentId());
            preparedStatement.setString(2, submission.getStudentId());
            preparedStatement.setString(3, submission.getContent());
            preparedStatement.setDate(4, submission.getSubmissionDate());

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
    public List<Submission> getAllSubmissions() {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Submission> submissions = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement(SQL_GET_ALL_SUBMISSIONS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Submission submission = new Submission();
                submission.setAssignmentId(resultSet.getInt("assignment_id"));
                submission.setStudentId(resultSet.getString("student_id"));
                submission.setContent(resultSet.getString("content"));
                submission.setSubmissionDate(resultSet.getDate("submission_date"));
                submission.setFeedback(resultSet.getString("feedback"));
                submission.setGrade(resultSet.getInt("grade"));
                submissions.add(submission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }

        return submissions;
    }

    @Override
    public List<Submission> getSubmissionsByAssignmentId(int assignmentId) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Submission> submissions = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement(SQL_GET_SUBMISSIONS_BY_ASSIGNMENT_ID);
            preparedStatement.setInt(1, assignmentId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Submission submission = new Submission();
                submission.setAssignmentId(resultSet.getInt("assignment_id"));
                submission.setStudentId(resultSet.getString("student_id"));
                submission.setContent(resultSet.getString("content"));
                submission.setSubmissionDate(resultSet.getDate("submission_date"));
                submission.setFeedback(resultSet.getString("feedback"));
                submission.setGrade(resultSet.getInt("grade"));
                submissions.add(submission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }

        return submissions;
    }

    @Override
    public Submission getSubmission(String studentId, int assignmentId) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Submission submission = null;

        try {
            preparedStatement = conn.prepareStatement(SQL_GET_SUBMISSION);
            preparedStatement.setInt(1, assignmentId);
            preparedStatement.setString(2, studentId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                submission = new Submission();
                submission.setAssignmentId(resultSet.getInt("assignment_id"));
                submission.setStudentId(resultSet.getString("student_id"));
                submission.setContent(resultSet.getString("content"));
                submission.setSubmissionDate(resultSet.getDate("submission_date"));
                submission.setFeedback(resultSet.getString("feedback"));
                submission.setGrade(resultSet.getInt("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }

        return submission;
    }

    @Override
    public List<Submission> getSubmissionsByStudentId(String studentId) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Submission> submissions = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM submissions WHERE student_id=?");
            preparedStatement.setString(1, studentId);
            System.out.println("dao:"+studentId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Submission submission = new Submission();
                submission.setAssignmentId(resultSet.getInt("assignment_id"));
                submission.setStudentId(resultSet.getString("student_id"));
                submission.setContent(resultSet.getString("content"));
                submission.setSubmissionDate(resultSet.getDate("submission_date"));
                submission.setFeedback(resultSet.getString("feedback"));
                submission.setGrade(resultSet.getInt("grade"));
                submissions.add(submission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, resultSet);
        }

        return submissions;
    }


    @Override
    public boolean updateSubmission(String studentId, int assignmentId, String feedback, int grade) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = conn.prepareStatement(SQL_UPDATE_SUBMISSION);
            preparedStatement.setString(1, feedback);
            preparedStatement.setInt(2, grade);
            preparedStatement.setInt(3, assignmentId);
            preparedStatement.setString(4, studentId);

            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatement, null);
        }
    }
}
