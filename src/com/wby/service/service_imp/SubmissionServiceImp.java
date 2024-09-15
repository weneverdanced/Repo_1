package com.wby.service.service_imp;

import com.wby.dao.SubmissionDao;
import com.wby.dao.dao_imp.SubmissionDaoImp;
import com.wby.entity.Submission;
import com.wby.service.SubmissionService;

import java.util.List;

public class SubmissionServiceImp implements SubmissionService {
    private SubmissionDao submissionDao = new SubmissionDaoImp();

    @Override
    public boolean addSubmission(Submission submission) {
        return submissionDao.addSubmission(submission);
    }

    @Override
    public List<Submission> getSubmissionsByAssignmentId(int assignmentId) {
        return submissionDao.getSubmissionsByAssignmentId(assignmentId);
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return submissionDao.getAllSubmissions();
    }

    @Override
    public Submission getSubmission(String studentId, int assignmentId) {
        return submissionDao.getSubmission(studentId, assignmentId);
    }

    @Override
    public boolean updateSubmission(String studentId, int assignmentId, String feedback, int grade) {
        return submissionDao.updateSubmission(studentId, assignmentId, feedback, grade);
    }

    @Override
    public List<Submission> getSubmissionsByStudentId(String studentId){
        return submissionDao.getSubmissionsByStudentId(studentId);
    }
}
