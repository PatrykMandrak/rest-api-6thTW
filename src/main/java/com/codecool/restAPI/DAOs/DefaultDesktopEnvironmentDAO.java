package com.codecool.restAPI.DAOs;

import com.codecool.restAPI.Models.DefaultDesktopEnvironment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DefaultDesktopEnvironmentDAO implements IDAO<DefaultDesktopEnvironment, Long> {
    private Session currentSession;

    private Transaction currentTransaction;

    @Override
    public void persist(DefaultDesktopEnvironment entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(DefaultDesktopEnvironment entity) {
        getCurrentSession().merge(entity);
    }

    @Override
    public DefaultDesktopEnvironment findById(Long id) {
        DefaultDesktopEnvironment defaultDesktopEnvironment = getCurrentSession().get(DefaultDesktopEnvironment.class, id);
        return defaultDesktopEnvironment;
    }

    @Override
    public void delete(DefaultDesktopEnvironment entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DefaultDesktopEnvironment> findAll() {
        List<DefaultDesktopEnvironment> defaultDesktopEnvironments = (List<DefaultDesktopEnvironment>) getCurrentSession().createQuery("from DefaultDesktopEnvironment ").list();
        return defaultDesktopEnvironments;
    }

    @Override
    public void deleteAll() {
        List<DefaultDesktopEnvironment> entityList = findAll();
        for (DefaultDesktopEnvironment entity : entityList) {
            delete(entity);
        }
    }

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
}
