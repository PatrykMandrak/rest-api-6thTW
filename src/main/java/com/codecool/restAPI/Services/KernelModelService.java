package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.KernelDAO;
import com.codecool.restAPI.Models.Kernel;
import com.codecool.restAPI.Models.KernelType;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class KernelModelService implements IModelService<Kernel> {

    private static KernelDAO kernelDAO;
    private KernelTypeModelService kernelTypeService = new KernelTypeModelService();

    public KernelModelService() {
        kernelDAO = new KernelDAO();
    }

    @Override
    public void persist(Kernel entity) {
        kernelDAO.openCurrentSessionWithTransaction();
        kernelDAO.persist(entity);
        kernelDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public void update(Kernel entity) {
        kernelDAO.openCurrentSessionWithTransaction();
        kernelDAO.update(entity);
        kernelDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public Kernel findById(Long id) {
        kernelDAO.openCurrentSession();
        Kernel kernel = kernelDAO.findById(id);
        kernelDAO.closeCurrentSession();
        return kernel;
    }

    @Override
    public void delete(Long id) {
        kernelDAO.openCurrentSessionWithTransaction();
        Kernel kernel = kernelDAO.findById(id);
        kernelDAO.delete(kernel);
        kernelDAO.closeCurrentSessionWithTransaction();
    }

    @Override
    public List<Kernel> findAll() {
        kernelDAO.openCurrentSession();
        List<Kernel> kernels = kernelDAO.findAll();
        kernelDAO.closeCurrentSession();
        return kernels;
    }

    @Override
    public void deleteAll() {
        kernelDAO.openCurrentSessionWithTransaction();
        kernelDAO.deleteAll();
        kernelDAO.closeCurrentSessionWithTransaction();
    }

    public KernelDAO kernelDAO() {
        return kernelDAO;
    }

    public String getKernelAsJson(Map<String, String> uriMap) throws JsonProcessingException {
        ObjectToJsonService objectToJsonService = new ObjectToJsonService();

        if (!uriMap.containsKey("id")) {
            List<Kernel> kernelList = findAll();
            return objectToJsonService.convertObjectToJson(kernelList);
        } else {
            Kernel kernel = findById(Long.parseLong(uriMap.get("id")));
            return objectToJsonService.convertObjectToJson(kernel);
        }
    }

    public String addNewKernel(HttpServletRequest request) {
        try {
            String kernelName = request.getParameter("name");
            String kernelDescription = request.getParameter("description");
            Long kernelTypeId = Long.parseLong(request.getParameter("kernelTypeId"));

            KernelType kernelType = kernelTypeService.findById(kernelTypeId);

            Kernel newKernel = new Kernel(kernelName, kernelDescription, kernelType);
            persist(newKernel);

            return "Post works";

        } catch (Exception e) {
            e.printStackTrace();

            return "Post doesn't work:\n\n" + e.toString();
        }
    }

    public String deleteKernel(HttpServletRequest request) {
        Long kernelId = Long.parseLong(request.getParameter("id"));

        if (checkIfExistById(kernelId)) {
            delete(kernelId);

            return "Delete works";
        } else {
            return "Wrong id parameter. Check operation system id that You want to delete";
        }
    }

    private boolean checkIfExistById(Long id) {
        return findById(id) != null;
    }

    public String updateKernel(HttpServletRequest request) {
        try {
            // Comparing many variables to null, that's to allow user to modify only chosen fields
            Long id = Long.parseLong(request.getParameter("id"));

            KernelType kernelType = null;
            if (request.getParameter("kernelTypeId") != null) {
                Long kernelTypeId = Long.parseLong(request.getParameter("kernelTypeId"));
                kernelType = kernelTypeService.findById(kernelTypeId);
            }

            String name = request.getParameter("name");
            String description = request.getParameter("description");

            Kernel kernel = findById(id);


            if (kernelType != null)
                kernel.setKernelType(kernelType);
            if (name != null)
                kernel.setName(name);
            if (description != null)
                kernel.setDescription(description);

            update(kernel);

            return "UPDATED";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return "ERROR";
    }
}
