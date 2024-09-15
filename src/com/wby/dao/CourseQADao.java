package com.wby.dao;

import com.wby.entity.CourseQA;

import java.util.List;

public interface CourseQADao {
    boolean addQuestion(CourseQA courseQA);
    boolean answerQuestion(int questionId, String answer, String teacherId);
    List<CourseQA> getQuestionsByStudentId(String studentId);
    List<CourseQA> getAllQuestions();
}
