package com.codecool.restAPI.Services;

import com.codecool.restAPI.DAOs.KernelTypeDAO;
import com.codecool.restAPI.Models.KernelType;

import java.util.List;

public class KernelTypeService {
    private static KernelTypeDAO kernelTypeDAO;

    public KernelTypeService() {
        kernelTypeDAO = new KernelTypeDAO();
    }

    public void persist(KernelType entity) {
        kernelTypeDAO.openCurrentSessionwithTransaction();
        kernelTypeDAO.persist(entity);
        kernelTypeDAO.closeCurrentSessionwithTransaction();
    }

    public void update(KernelType entity) {
        kernelTypeDAO.openCurrentSessionwithTransaction();
        kernelTypeDAO.update(entity);
        kernelTypeDAO.closeCurrentSessionwithTransaction();
    }

    public KernelType findById(String id) {
        kernelTypeDAO.openCurrentSession();
        KernelType kernelType = kernelTypeDAO.findById(id);
        kernelTypeDAO.closeCurrentSession();
        return kernelType;
    }

    public void delete(String id) {
        kernelTypeDAO.openCurrentSessionwithTransaction();
        KernelType kernelType = kernelTypeDAO.findById(id);
        kernelTypeDAO.delete(kernelType);
        kernelTypeDAO.closeCurrentSessionwithTransaction();
    }

    public List<KernelType> findAll() {
        kernelTypeDAO.openCurrentSession();
        List<KernelType> kernelTypes = kernelTypeDAO.findAll();
        kernelTypeDAO.closeCurrentSession();
        return kernelTypes;
    }

    public void deleteAll() {
        kernelTypeDAO.openCurrentSessionwithTransaction();
        kernelTypeDAO.deleteAll();
        kernelTypeDAO.closeCurrentSessionwithTransaction();
    }

    public KernelTypeDAO kernelDAO() {
        return kernelTypeDAO;
    }
}
