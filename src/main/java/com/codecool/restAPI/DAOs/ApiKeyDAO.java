package com.codecool.restAPI.DAOs;

import com.codecool.restAPI.Models.ApiKey;
import com.codecool.restAPI.Models.KernelType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ApiKeyDAO {
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

    public void persist(ApiKey entity) {
        getCurrentSession().save(entity);
    }

    public void update(ApiKey entity) {
        getCurrentSession().merge(entity);
    }


    public ApiKey findById(Long id) {
        ApiKey apiKey = (ApiKey) getCurrentSession().get(ApiKey.class, id);
        return apiKey;
    }

    public void delete(ApiKey entity) {
        getCurrentSession().delete(entity);
    }

    @SuppressWarnings("unchecked")
    public List<ApiKey> findAll() {
        List<ApiKey> apiKeys = (List<ApiKey>) getCurrentSession().createQuery("from ApiKey").list();
        return apiKeys;
    }
}

