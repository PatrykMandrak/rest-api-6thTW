package com.codecool.restAPI.DAOs;

import com.codecool.restAPI.Models.KernelType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class KernelTypeDAO {
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
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(KernelType.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory =  configuration.buildSessionFactory(builder.build());
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

    public KernelType findById(String id) {
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
