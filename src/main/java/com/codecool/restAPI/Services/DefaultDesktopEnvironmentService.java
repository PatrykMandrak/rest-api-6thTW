package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.DefaultDesktopEnvironmentDAO;
import com.codecool.restAPI.Models.DefaultDesktopEnvironment;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class DefaultDesktopEnvironmentService {
    private static DefaultDesktopEnvironmentDAO defaultDesktopEnvironmentDAO;

    public DefaultDesktopEnvironmentService() {
        defaultDesktopEnvironmentDAO = new DefaultDesktopEnvironmentDAO();
    }

    public void persist(DefaultDesktopEnvironment entity) {
        defaultDesktopEnvironmentDAO.openCurrentSessionWithTransaction();
        defaultDesktopEnvironmentDAO.persist(entity);
        defaultDesktopEnvironmentDAO.closeCurrentSessionWithTransaction();
    }

    public void update(DefaultDesktopEnvironment entity) {
        defaultDesktopEnvironmentDAO.openCurrentSessionWithTransaction();
        defaultDesktopEnvironmentDAO.update(entity);
        defaultDesktopEnvironmentDAO.closeCurrentSessionWithTransaction();
    }

    public DefaultDesktopEnvironment findById(Long id) {
        defaultDesktopEnvironmentDAO.openCurrentSession();
        DefaultDesktopEnvironment defaultDesktopEnvironment = defaultDesktopEnvironmentDAO.findById(id);
        defaultDesktopEnvironmentDAO.closeCurrentSession();
        return defaultDesktopEnvironment;
    }

    public void delete(Long id) {
        defaultDesktopEnvironmentDAO.openCurrentSessionWithTransaction();
        DefaultDesktopEnvironment operationSystem = defaultDesktopEnvironmentDAO.findById(id);
        defaultDesktopEnvironmentDAO.delete(operationSystem);
        defaultDesktopEnvironmentDAO.closeCurrentSessionWithTransaction();
    }

    public List<DefaultDesktopEnvironment> findAll() {
        defaultDesktopEnvironmentDAO.openCurrentSession();
        List<DefaultDesktopEnvironment> defaultDesktopEnvironments = defaultDesktopEnvironmentDAO.findAll();
        defaultDesktopEnvironmentDAO.closeCurrentSession();
        return defaultDesktopEnvironments;
    }

    public void deleteAll() {
        defaultDesktopEnvironmentDAO.openCurrentSessionWithTransaction();
        defaultDesktopEnvironmentDAO.deleteAll();
        defaultDesktopEnvironmentDAO.closeCurrentSessionWithTransaction();
    }

    public DefaultDesktopEnvironmentDAO operationSystemDAO() {
        return defaultDesktopEnvironmentDAO;
    }

    public String getDefaultDekstopEnvironmentAsJson(Map<String, String> uriMap) throws JsonProcessingException {
        ObjectToJsonService objectToJsonService = new ObjectToJsonService();

        if (!uriMap.containsKey("id")) {
            List<DefaultDesktopEnvironment> defaultDesktopEnvironmentList = findAll();
            return objectToJsonService.convertObjectToJson(defaultDesktopEnvironmentList);
        } else {
            DefaultDesktopEnvironment defaultDesktopEnvironment = findById(Long.parseLong(uriMap.get("id")));
            return objectToJsonService.convertObjectToJson(defaultDesktopEnvironment);
        }
    }

    public String addNewDefaultDesktopEnvironments(HttpServletRequest request) {
        try {
            String desktopEnviromentName = request.getParameter("name");

            DefaultDesktopEnvironment newDefaultDesktopEnvironment = new DefaultDesktopEnvironment(desktopEnviromentName);
            persist(newDefaultDesktopEnvironment);

            return "Post works";
        } catch (Exception e) {
            e.printStackTrace();

            return "Post doesn't work:\n\n" + e.toString();
        }
    }

    public String deleteDesktopEnvironment(HttpServletRequest request) {
        Long desktopEnvironmentId = Long.parseLong(request.getParameter("id"));

        if (checkIfExistById(desktopEnvironmentId)) {
            delete(desktopEnvironmentId);

            return "Delete works";
        } else {
            return "Wrong id parameter. Check operation system id that You want to delete";
        }
    }

    private boolean checkIfExistById(Long id) {
        return findById(id) != null;
    }

    public String udateDefaultDesktopEnvironments(HttpServletRequest request) {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");

            DefaultDesktopEnvironment defaultDesktopEnvironment = findById(id);
            defaultDesktopEnvironment.setName(name);

            update(defaultDesktopEnvironment);

            return "UPDATED";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
