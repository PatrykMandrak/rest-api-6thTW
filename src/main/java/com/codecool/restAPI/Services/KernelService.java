package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.KernelDAO;
import com.codecool.restAPI.Models.Kernel;

import java.util.List;

public class KernelService {

    private static KernelDAO kernelDAO;

    public KernelService() {
        kernelDAO = new KernelDAO();
    }

    public void persist(Kernel entity) {
        kernelDAO.openCurrentSessionwithTransaction();
        kernelDAO.persist(entity);
        kernelDAO.closeCurrentSessionwithTransaction();
    }

    public void update(Kernel entity) {
        kernelDAO.openCurrentSessionwithTransaction();
        kernelDAO.update(entity);
        kernelDAO.closeCurrentSessionwithTransaction();
    }

    public Kernel findById(Long id) {
        kernelDAO.openCurrentSession();
        Kernel kernel = kernelDAO.findById(id);
        kernelDAO.closeCurrentSession();
        return kernel;
    }

    public void delete(Long id) {
        kernelDAO.openCurrentSessionwithTransaction();
        Kernel kernel = kernelDAO.findById(id);
        kernelDAO.delete(kernel);
        kernelDAO.closeCurrentSessionwithTransaction();
    }

    public List<Kernel> findAll() {
        kernelDAO.openCurrentSession();
        List<Kernel> kernels = kernelDAO.findAll();
        kernelDAO.closeCurrentSession();
        return kernels;
    }

    public void deleteAll() {
        kernelDAO.openCurrentSessionwithTransaction();
        kernelDAO.deleteAll();
        kernelDAO.closeCurrentSessionwithTransaction();
    }

    public KernelDAO kernelDAO() {
        return kernelDAO;
    }
}
