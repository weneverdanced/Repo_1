package com.wby.service.service_imp;

import com.wby.dao.AssignmentDao;
import com.wby.dao.dao_imp.AssignmentDaoImp;
import com.wby.entity.Assignment;
import com.wby.service.AssignmentService;

import java.util.List;

public class AssignmentServiceImp implements AssignmentService {
    private AssignmentDao assignmentDao = new AssignmentDaoImp();

    @Override
    public boolean addAssignment(Assignment assignment) {
        return assignmentDao.addAssignment(assignment);
    }

    @Override
    public List<Assignment> getAssignmentsByTeacherId(String teacherId) {
        return assignmentDao.getAssignmentsByTeacherId(teacherId);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentDao.getAllAssignments();
    }
}
