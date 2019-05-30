package com.codecool.restAPI.DAOs;

import com.codecool.restAPI.Models.Kernel;
import com.codecool.restAPI.Models.KernelType;
import com.codecool.restAPI.Models.OperationSystem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class KernelTypeDAO implements IDAO<KernelType, Long>{
    private Session currentSession;

    private Transaction currentTransaction;

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        // NEED FOR SPEEDFACTOR
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(KernelType entity) {
        getCurrentSession().save(entity);
    }

    public void update(KernelType entity) {
        getCurrentSession().merge(entity);
    }

    public boolean checkIfExistById(Long id) {
        return findById(id) != null;
    }

    public KernelType findById(Long id) {
        KernelType kernelType = (KernelType) getCurrentSession().get(KernelType.class, id);
        return kernelType;
    }

    public void delete(KernelType entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<KernelType> findAll() {
        List<KernelType> kernelTypes = (List<KernelType>) getCurrentSession().createQuery("from KernelType").list();
        return kernelTypes;
    }

    public void deleteAll() {
        List<KernelType> entityList = findAll();
        for (KernelType entity : entityList) {
            delete(entity);
        }
    }
}
