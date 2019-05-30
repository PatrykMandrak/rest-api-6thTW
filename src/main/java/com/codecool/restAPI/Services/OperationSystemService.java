package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.OperationSystemDAO;
import com.codecool.restAPI.Models.DefaultDesktopEnvironment;
import com.codecool.restAPI.Models.Kernel;
import com.codecool.restAPI.Models.OperationSystem;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class OperationSystemService {
    private static OperationSystemDAO operationSystemDAO = new OperationSystemDAO();
    private KernelService kernelService = new KernelService();
    private DefaultDesktopEnvironmentService defaultDesktopEnvironmentService = new DefaultDesktopEnvironmentService();

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

    public OperationSystem findById(Long id) {
        operationSystemDAO.openCurrentSession();
        OperationSystem operationSystem = operationSystemDAO.findById(id);
        operationSystemDAO.closeCurrentSession();
        return operationSystem;
    }

    public void delete(Long id) {
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

    public String getOperationSystemAsJson(List<String> splittedUri) throws JsonProcessingException {
        ObjectToJsonService objectToJsonService = new ObjectToJsonService();

        if (splittedUri.size() == 2) {
            List<OperationSystem> operationSystemList = findAll();
            return objectToJsonService.convertObjectToJson(operationSystemList);
        } else if (splittedUri.size() == 3) {
            OperationSystem operationSystem = findById(Long.getLong(splittedUri.get(3)));
            return objectToJsonService.convertObjectToJson(operationSystem);
        } else {
            return "Wrong URI";
        }
    }

    public String addNewOperationSystem(HttpServletRequest request) {
        try {
            String operationSystemName = request.getParameter("name");
            Long kernelId = Long.parseLong(request.getParameter("kernelId"));
            Long defaultDesktopEnvironmentId = Long.parseLong(request.getParameter("desktopEnvironmentId"));

            Kernel kernel = kernelService.findById(kernelId);
            DefaultDesktopEnvironment defaultDesktopEnvironment = defaultDesktopEnvironmentService.findById(defaultDesktopEnvironmentId);

            OperationSystem newOperationSystem = new OperationSystem(operationSystemName, kernel, defaultDesktopEnvironment);
            persist(newOperationSystem);

            return "Post works";

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());

            return "Post doesn't work:\n\n" + e.toString();
        }
    }

    public String deleteOperationSystem(HttpServletRequest request) {
        Long operationSystemId = Long.parseLong(request.getParameter("id"));

        if (checkIfExistById(operationSystemId)) {
            delete(operationSystemId);

            return "Delete works";
        } else {
            return "Wrong id parameter. Check operation system id that You want to delete";
        }
    }

    private boolean checkIfExistById(Long id) {
        return findById(id) != null;
    }
}
