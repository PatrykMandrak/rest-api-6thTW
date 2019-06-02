package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.KernelTypeDAO;
import com.codecool.restAPI.Models.KernelType;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class KernelTypeModelService implements IModelService<KernelType> {
    private static KernelTypeDAO kernelTypeDAO;

    public KernelTypeModelService() {
        kernelTypeDAO = new KernelTypeDAO();
    }

    @Override
    public void persist(KernelType entity) {
        kernelTypeDAO.openCurrentSessionWithTransaction();
        kernelTypeDAO.persist(entity);
        kernelTypeDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(KernelType entity) {
        kernelTypeDAO.openCurrentSessionWithTransaction();
        kernelTypeDAO.update(entity);
        kernelTypeDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public KernelType findById(Long id) {
        kernelTypeDAO.openCurrentSession();
        KernelType kernelType = kernelTypeDAO.findById(id);
        kernelTypeDAO.closeCurrentSession();
        return kernelType;
    }

    @Override
    public void delete(Long id) {
        kernelTypeDAO.openCurrentSessionWithTransaction();
        KernelType kernelType = kernelTypeDAO.findById(id);
        kernelTypeDAO.delete(kernelType);
        kernelTypeDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public List<KernelType> findAll() {
        kernelTypeDAO.openCurrentSession();
        List<KernelType> kernelTypes = kernelTypeDAO.findAll();
        kernelTypeDAO.closeCurrentSession();
        return kernelTypes;
    }

    @Override
    public void deleteAll() {
        kernelTypeDAO.openCurrentSessionWithTransaction();
        kernelTypeDAO.deleteAll();
        kernelTypeDAO.closeCurrentSessionWithTransaction();
    }

    public KernelTypeDAO kernelTypeDAO() {
        return kernelTypeDAO;
    }

    public String getKernelTypeAsJson(Map<String, String> uriMap) throws JsonProcessingException {
        ObjectToJsonService objectToJsonService = new ObjectToJsonService();

        if (!uriMap.containsKey("id")) {
            List<KernelType> kernelTypeList = findAll();
            return objectToJsonService.convertObjectToJson(kernelTypeList);
        } else {
            KernelType kernelType = findById(Long.parseLong(uriMap.get("id")));
            return objectToJsonService.convertObjectToJson(kernelType);
        }
    }

    public String addNewKernelType(HttpServletRequest request) {
        try {
            String kernelTypeName = request.getParameter("name");
            String kernelTypeDescription = request.getParameter("description");

            KernelType newKernelType = new KernelType(kernelTypeName, kernelTypeDescription);
            persist(newKernelType);

            return "Post works";

        } catch (Exception e) {
            e.printStackTrace();

            return "Post doesn't work:\n\n" + e.toString();
        }
    }

    public String deleteKernelType(HttpServletRequest request) {
        Long kernelTypeId = Long.parseLong(request.getParameter("id"));

        if (checkIfExistById(kernelTypeId)) {
            delete(kernelTypeId);

            return "Delete works";
        } else {
            return "Wrong id parameter. Check operation system id that You want to delete";
        }
    }

    private boolean checkIfExistById(Long id) {
        return findById(id) != null;
    }

    public String updateKernelType(HttpServletRequest request) {
        try {
            // Comparing some variables to null, that's to allow user to modify only chosen fields
            Long id = Long.parseLong(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");

            KernelType kernelType = findById(id);

            if(name != null)
            kernelType.setName(name);
            if(description != null)
            kernelType.setDescription(description);

            update(kernelType);

            return "UPDATED";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
