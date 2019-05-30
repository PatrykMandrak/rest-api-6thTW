package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.KernelDAO;
import com.codecool.restAPI.Models.Kernel;
import com.codecool.restAPI.Models.KernelType;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class KernelService {

    private static KernelDAO kernelDAO;
    private KernelTypeService kernelTypeService = new KernelTypeService();

    public KernelService() {
        kernelDAO = new KernelDAO();
    }

    public void persist(Kernel entity) {
        kernelDAO.openCurrentSessionWithTransaction();
        kernelDAO.persist(entity);
        kernelDAO.closeCurrentSessionWithTransaction();
    }

    public void update(Kernel entity) {
        kernelDAO.openCurrentSessionWithTransaction();
        kernelDAO.update(entity);
        kernelDAO.closeCurrentSessionWithTransaction();
    }

    public Kernel findById(Long id) {
        kernelDAO.openCurrentSession();
        Kernel kernel = kernelDAO.findById(id);
        kernelDAO.closeCurrentSession();
        return kernel;
    }

    public void delete(Long id) {
        kernelDAO.openCurrentSessionWithTransaction();
        Kernel kernel = kernelDAO.findById(id);
        kernelDAO.delete(kernel);
        kernelDAO.closeCurrentSessionWithTransaction();
    }

    public List<Kernel> findAll() {
        kernelDAO.openCurrentSession();
        List<Kernel> kernels = kernelDAO.findAll();
        kernelDAO.closeCurrentSession();
        return kernels;
    }

    public void deleteAll() {
        kernelDAO.openCurrentSessionWithTransaction();
        kernelDAO.deleteAll();
        kernelDAO.closeCurrentSessionWithTransaction();
    }

    public KernelDAO kernelDAO() {
        return kernelDAO;
    }

    public String getKernelAsJson(List<String> splittedUri) throws JsonProcessingException {
        ObjectToJsonService objectToJsonService = new ObjectToJsonService();

        if (splittedUri.size() == 2) {
            List<Kernel> kernelList = findAll();
            return objectToJsonService.convertObjectToJson(kernelList);
        } else if (splittedUri.size() == 3) {
            Kernel kernel = findById(Long.getLong(splittedUri.get(3)));
            return objectToJsonService.convertObjectToJson(kernel);
        } else {
            return "Your URL is too creazy brooooooo ";
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
}
