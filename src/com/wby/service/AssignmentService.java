package com.wby.service;

import com.wby.entity.Assignment;
import java.util.List;

public interface AssignmentService {
    boolean addAssignment(Assignment assignment);
    List<Assignment> getAssignmentsByTeacherId(String teacherId);
    List<Assignment> getAllAssignments();
}
