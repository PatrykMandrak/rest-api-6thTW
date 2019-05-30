package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.KernelTypeDAO;
import com.codecool.restAPI.Models.KernelType;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class KernelTypeService {
    private static KernelTypeDAO kernelTypeDAO;

    public KernelTypeService() {
        kernelTypeDAO = new KernelTypeDAO();
    }

    public void persist(KernelType entity) {
        kernelTypeDAO.openCurrentSessionWithTransaction();
        kernelTypeDAO.persist(entity);
        kernelTypeDAO.closeCurrentSessionWithTransaction();
    }

    public void update(KernelType entity) {
        kernelTypeDAO.openCurrentSessionWithTransaction();
        kernelTypeDAO.update(entity);
        kernelTypeDAO.closeCurrentSessionWithTransaction();
    }

    public KernelType findById(Long id) {
        kernelTypeDAO.openCurrentSession();
        KernelType kernelType = kernelTypeDAO.findById(id);
        kernelTypeDAO.closeCurrentSession();
        return kernelType;
    }

    public void delete(Long id) {
        kernelTypeDAO.openCurrentSessionWithTransaction();
        KernelType kernelType = kernelTypeDAO.findById(id);
        kernelTypeDAO.delete(kernelType);
        kernelTypeDAO.closeCurrentSessionWithTransaction();
    }

    public List<KernelType> findAll() {
        kernelTypeDAO.openCurrentSession();
        List<KernelType> kernelTypes = kernelTypeDAO.findAll();
        kernelTypeDAO.closeCurrentSession();
        return kernelTypes;
    }

    public void deleteAll() {
        kernelTypeDAO.openCurrentSessionWithTransaction();
        kernelTypeDAO.deleteAll();
        kernelTypeDAO.closeCurrentSessionWithTransaction();
    }

    public KernelTypeDAO kernelTypeDAO() {
        return kernelTypeDAO;
    }

    public String getKernelTypeAsJson(List<String> splittedUri) throws JsonProcessingException {
        ObjectToJsonService objectToJsonService = new ObjectToJsonService();

        if (splittedUri.size() == 2) {
            List<KernelType> kernelTypeList = findAll();
            return objectToJsonService.convertObjectToJson(kernelTypeList);
        } else if (splittedUri.size() == 3) {
            KernelType kernelType = findById(Long.getLong(splittedUri.get(3)));
            return objectToJsonService.convertObjectToJson(kernelType);
        } else {
            return "Your URL is too creazy brooooooo ";
        }
    }

    public String addNewKernelType(HttpServletRequest request) {
        return null;
    }

    public String updateKernelType(HttpServletRequest request) {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");


            KernelType kernelType = findById(id);
            kernelType.setName(name);
            kernelType.setDescription(description);

            update(kernelType);

            return "UPDATED";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
