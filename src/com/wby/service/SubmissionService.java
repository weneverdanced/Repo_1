package com.wby.service;

import com.wby.entity.Submission;
import java.util.List;

public interface SubmissionService {
    boolean addSubmission(Submission submission);
    List<Submission> getSubmissionsByAssignmentId(int assignmentId);
    List<Submission> getAllSubmissions() ;
    Submission getSubmission(String studentId, int assignmentId) ;
    boolean updateSubmission(String studentId, int assignmentId, String feedback, int grade);
    public List<Submission> getSubmissionsByStudentId(String studentId);
}
