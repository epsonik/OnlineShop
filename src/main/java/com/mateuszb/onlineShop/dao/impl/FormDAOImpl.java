package com.mateuszb.onlineShop.dao.impl;


import com.mateuszb.onlineShop.dao.FormDAO;
import com.mateuszb.onlineShop.dto.Form;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class FormDAOImpl implements FormDAO {

    private SessionFactory sessionFactory;
    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertForm(Form form) {
        session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(form);
        tx.commit();
        session.close();
    }

    public Form getDataByLogin(String login) {
        session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Form WHERE login = :login");
        query.setParameter("login", login);
        List<Form> personList = query.list();
        session.close();
        return personList.get(0);
    }
}