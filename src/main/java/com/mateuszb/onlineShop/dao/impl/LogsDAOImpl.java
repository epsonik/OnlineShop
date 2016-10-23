package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.LogsDAO;
import com.mateuszb.onlineShop.dto.Logs;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LogsDAOImpl implements LogsDAO {

    private Session session;
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insert(String description) {
        Logs logs = new Logs();
        logs.setDescription(description);
        session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(logs);
        tx.commit();
        session.close();
    }
}
