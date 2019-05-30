package com.codecool.restAPI.DAOs;

import com.codecool.restAPI.Models.OperationSystem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OperationSystemDAO implements IDAO<OperationSystem, Long>{
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

    public void persist(OperationSystem entity) {
        getCurrentSession().save(entity);
    }

    public void update(OperationSystem entity) {
        getCurrentSession().merge(entity);
    }

    public boolean checkIfExistById(Long id) {
        return findById(id) != null;
    }

    public OperationSystem findById(Long id) {
        OperationSystem operationSystem = (OperationSystem) getCurrentSession().get(OperationSystem.class, id);
        return operationSystem;
    }

    public void delete(OperationSystem entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<OperationSystem> findAll() {
        List<OperationSystem> operationSystems = (List<OperationSystem>) getCurrentSession().createQuery("from OperationSystem").list();
        return operationSystems;
    }

    public void deleteAll() {
        List<OperationSystem> entityList = findAll();
        for (OperationSystem entity : entityList) {
            delete(entity);
        }
    }
}
