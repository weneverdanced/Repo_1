package com.wby.dao;

import com.wby.entity.Submission;
import java.util.List;

public interface SubmissionDao {
    boolean addSubmission(Submission submission);
    List<Submission> getSubmissionsByAssignmentId(int assignmentId);
    Submission getSubmission(String studentId, int assignmentId);
    boolean updateSubmission(String studentId, int assignmentId, String feedback, int grade);
    public List<Submission> getAllSubmissions();
    public List<Submission> getSubmissionsByStudentId(String studentId);
}
