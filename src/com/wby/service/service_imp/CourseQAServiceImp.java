package com.wby.service.service_imp;

import com.wby.dao.CourseQADao;
import com.wby.dao.dao_imp.CourseQADaoImp;
import com.wby.entity.CourseQA;
import com.wby.service.CourseQAService;

import java.util.List;

public class CourseQAServiceImp implements CourseQAService {
    private CourseQADao courseQADao = new CourseQADaoImp();

    @Override
    public boolean addQuestion(CourseQA courseQA) {
        return courseQADao.addQuestion(courseQA);
    }

    @Override
    public boolean answerQuestion(int questionId, String answer, String teacherId) {
        return courseQADao.answerQuestion(questionId, answer, teacherId);
    }

    @Override
    public List<CourseQA> getQuestionsByStudentId(String studentId) {
        return courseQADao.getQuestionsByStudentId(studentId);
    }

    @Override
    public List<CourseQA> getAllQuestions() {
        return courseQADao.getAllQuestions();
    }
}
