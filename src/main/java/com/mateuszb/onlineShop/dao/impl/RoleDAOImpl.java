package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.RoleDAO;
import com.mateuszb.onlineShop.dto.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RoleDAOImpl implements RoleDAO {

    private SessionFactory sessionFactory;
    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertRole(Role role) {
        session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(role);
        tx.commit();
        session.close();
    }
}
