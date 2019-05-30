package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.KernelDAO;
import com.codecool.restAPI.Models.Kernel;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class KernelService {

    private static KernelDAO kernelDAO;

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
        return null;
    }

    public String udateKernel(HttpServletRequest request) {
        return null;
    }
}
