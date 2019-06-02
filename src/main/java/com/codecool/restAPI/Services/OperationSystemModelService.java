package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.OperationSystemDAO;
import com.codecool.restAPI.Models.DefaultDesktopEnvironment;
import com.codecool.restAPI.Models.Kernel;
import com.codecool.restAPI.Models.OperationSystem;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class OperationSystemModelService implements IModelService<OperationSystem> {
    private static OperationSystemDAO operationSystemDAO = new OperationSystemDAO();
    private KernelModelService kernelService = new KernelModelService();
    private DefaultDesktopEnvironmentModelService defaultDesktopEnvironmentService = new DefaultDesktopEnvironmentModelService();

    public OperationSystemModelService() {
        operationSystemDAO = new OperationSystemDAO();
    }

    @Override
    public void persist(OperationSystem entity) {
        operationSystemDAO.openCurrentSessionWithTransaction();
        operationSystemDAO.persist(entity);
        operationSystemDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(OperationSystem entity) {
        operationSystemDAO.openCurrentSessionWithTransaction();
        operationSystemDAO.update(entity);
        operationSystemDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public OperationSystem findById(Long id) {
        operationSystemDAO.openCurrentSession();
        OperationSystem operationSystem = operationSystemDAO.findById(id);
        operationSystemDAO.closeCurrentSession();
        return operationSystem;
    }

    @Override
    public void delete(Long id) {
        operationSystemDAO.openCurrentSessionWithTransaction();
        OperationSystem operationSystem = operationSystemDAO.findById(id);
        operationSystemDAO.delete(operationSystem);
        operationSystemDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public List<OperationSystem> findAll() {
        operationSystemDAO.openCurrentSession();
        List<OperationSystem> operationSystemList = operationSystemDAO.findAll();
        operationSystemDAO.closeCurrentSession();
        return operationSystemList;
    }

    @Override
    public void deleteAll() {
        operationSystemDAO.openCurrentSessionWithTransaction();
        operationSystemDAO.deleteAll();
        operationSystemDAO.closeCurrentSessionWithTransaction();
    }

    public String getOperationSystemAsJson(Map<String, String> uriMap) throws JsonProcessingException {
        ObjectToJsonService objectToJsonService = new ObjectToJsonService();

        if (!uriMap.containsKey("id")) {
            List<OperationSystem> operationSystemList = findAll();
            return objectToJsonService.convertObjectToJson(operationSystemList);
        } else if (uriMap.size() == 3) {
            OperationSystem operationSystem = findById(Long.parseLong(uriMap.get("id")));
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

    public String updateOperationSystem(HttpServletRequest request) {
        try {
            // Comparing many variables to null, that's to allow user to modify only chosen fields
            String operationSystemName = request.getParameter("name");
            Long itemToUpdateId = Long.parseLong(request.getParameter("id"));

            Kernel kernel = null;
            if(request.getParameter("kernelId") != null) {
                Long kernelId = Long.parseLong(request.getParameter("kernelId"));
                kernel = kernelService.findById(kernelId);
            }

            DefaultDesktopEnvironment defaultDesktopEnvironment = null;
            if(request.getParameter("desktopEnvironmentId") != null) {
                Long defaultDesktopEnvironmentId = Long.parseLong(request.getParameter("desktopEnvironmentId"));
                defaultDesktopEnvironment = defaultDesktopEnvironmentService.findById(defaultDesktopEnvironmentId);
            }

            OperationSystem operationSystemToUpdate = findById(itemToUpdateId);

            if(operationSystemName != null)
            operationSystemToUpdate.setName(operationSystemName);

            if(kernel != null)
            operationSystemToUpdate.setKernel(kernel);

            if(defaultDesktopEnvironment != null)
            operationSystemToUpdate.setDefaultDesktopEnvironment(defaultDesktopEnvironment);

            if(operationSystemToUpdate != null)
            update(operationSystemToUpdate);

            return "UPDATED"
                    ;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return "ERROR";

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
