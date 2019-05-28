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
}
