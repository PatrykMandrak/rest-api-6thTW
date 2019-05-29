package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.DefaultDesktopEnvironmentDAO;
import com.codecool.restAPI.Models.DefaultDesktopEnvironment;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

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

    public String getDefaultDekstopEnvironmentAsJson(List<String> splittedUri) throws JsonProcessingException {
        ObjectToJsonService objectToJsonService = new ObjectToJsonService();

        if (splittedUri.size() == 2) {
            List<DefaultDesktopEnvironment> operationSystemList = findAll();
            return objectToJsonService.convertObjectToJson(operationSystemList);
        } else if (splittedUri.size() == 3) {
            DefaultDesktopEnvironment operationSystem = findById(Long.getLong(splittedUri.get(3)));
            return objectToJsonService.convertObjectToJson(operationSystem);
        } else {
            return "Your URL is too creazy brooooooo ";
        }
    }
}
