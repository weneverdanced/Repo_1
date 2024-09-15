package com.wby.dao.dao_imp;

import com.wby.dao.CourseQADao;
import com.wby.entity.CourseQA;
import com.wby.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseQADaoImp implements CourseQADao {
    private static final String SQL_ADD_QUESTION = "INSERT INTO courseqa (student_id, question) VALUES (?, ?)";
    private static final String SQL_ANSWER_QUESTION = "UPDATE courseqa SET answer = ?, teacher_id = ? WHERE question_id = ?";
    private static final String SQL_GET_QUESTIONS_BY_STUDENT_ID = "SELECT * FROM courseqa WHERE student_id = ?";
    private static final String SQL_GET_ALL_QUESTIONS = "SELECT * FROM courseqa";

    @Override
    public boolean addQuestion(CourseQA courseQA) {
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_ADD_QUESTION)) {
            preparedStatement.setString(1, courseQA.getStudentId());
            preparedStatement.setString(2, courseQA.getQuestion());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean answerQuestion(int questionId, String answer, String teacherId) {
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_ANSWER_QUESTION)) {
            preparedStatement.setString(1, answer);
            preparedStatement.setString(2, teacherId);
            preparedStatement.setInt(3, questionId);
            System.out.println("dao:"+answer);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CourseQA> getQuestionsByStudentId(String studentId) {
        List<CourseQA> questions = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_QUESTIONS_BY_STUDENT_ID)) {
            preparedStatement.setString(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CourseQA courseQA = new CourseQA();
                    courseQA.setQuestionId(resultSet.getInt("question_id"));
                    courseQA.setStudentId(resultSet.getString("student_id"));
                    courseQA.setTeacherId(resultSet.getString("teacher_id"));
                    courseQA.setQuestion(resultSet.getString("question"));
                    courseQA.setAnswer(resultSet.getString("answer"));
                    questions.add(courseQA);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return questions;
    }

    @Override
    public List<CourseQA> getAllQuestions() {
        List<CourseQA> questions = new ArrayList<>();
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_QUESTIONS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                CourseQA courseQA = new CourseQA();
                courseQA.setQuestionId(resultSet.getInt("question_id"));
                courseQA.setStudentId(resultSet.getString("student_id"));
                courseQA.setTeacherId(resultSet.getString("teacher_id"));
                courseQA.setQuestion(resultSet.getString("question"));
                courseQA.setAnswer(resultSet.getString("answer"));
                questions.add(courseQA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return questions;
    }
}
