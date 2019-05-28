package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.OperationSystemDAO;
import com.codecool.restAPI.Models.OperationSystem;

import java.util.List;

public class OperationSystemService {
    private static OperationSystemDAO operationSystemDAO;

    public OperationSystemService() {
        operationSystemDAO = new OperationSystemDAO();
    }

    public void persist(OperationSystem entity) {
        operationSystemDAO.openCurrentSessionWithTransaction();
        operationSystemDAO.persist(entity);
        operationSystemDAO.closeCurrentSessionWithTransaction();
    }

    public void update(OperationSystem entity) {
        operationSystemDAO.openCurrentSessionWithTransaction();
        operationSystemDAO.update(entity);
        operationSystemDAO.closeCurrentSessionWithTransaction();
    }

    public OperationSystem findById(String id) {
        operationSystemDAO.openCurrentSession();
        OperationSystem operationSystem = operationSystemDAO.findById(id);
        operationSystemDAO.closeCurrentSession();
        return operationSystem;
    }

    public void delete(String id) {
        operationSystemDAO.openCurrentSessionWithTransaction();
        OperationSystem operationSystem = operationSystemDAO.findById(id);
        operationSystemDAO.delete(operationSystem);
        operationSystemDAO.closeCurrentSessionWithTransaction();
    }

    public List<OperationSystem> findAll() {
        operationSystemDAO.openCurrentSession();
        List<OperationSystem> operationSystemList = operationSystemDAO.findAll();
        operationSystemDAO.closeCurrentSession();
        return operationSystemList;
    }

    public void deleteAll() {
        operationSystemDAO.openCurrentSessionWithTransaction();
        operationSystemDAO.deleteAll();
        operationSystemDAO.closeCurrentSessionWithTransaction();
    }

    public OperationSystemDAO operationSystemDAO() {
        return operationSystemDAO;
    }
}
