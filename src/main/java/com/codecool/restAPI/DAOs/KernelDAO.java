package com.codecool.restAPI.DAOs;

import java.util.List;

import com.codecool.restAPI.Models.Kernel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class KernelDAO implements IDAO<Kernel, Long> {

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

    public void persist(Kernel entity) {
        getCurrentSession().save(entity);
    }

    public void update(Kernel entity) {
        getCurrentSession().merge(entity);
    }


    public Kernel findById(Long id) {
        Kernel kernel = (Kernel) getCurrentSession().get(Kernel.class, id);
        return kernel;
    }

    public void delete(Kernel entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Kernel> findAll() {
        List<Kernel> kernels = (List<Kernel>) getCurrentSession().createQuery("from Kernel ").list();
        return kernels;
    }

    public void deleteAll() {
        List<Kernel> entityList = findAll();
        for (Kernel entity : entityList) {
            delete(entity);
        }
    }

}
