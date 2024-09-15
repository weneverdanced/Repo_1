package com.wby.dao;

import com.wby.entity.Assignment;
import java.util.List;

public interface AssignmentDao {
    boolean addAssignment(Assignment assignment);
    List<Assignment> getAssignmentsByTeacherId(String teacherId);

    public int getMaxAssignmentId();

    public List<Assignment> getAllAssignments();
}
