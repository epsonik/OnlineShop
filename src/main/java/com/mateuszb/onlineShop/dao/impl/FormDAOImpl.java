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

    public boolean checkEmail(String email) {
        session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Form WHERE email = :email");
        query.setParameter("email", email);
        List<Form> emailList = query.list();
        return !emailList.isEmpty();
    }

    public boolean checkLogin(String login) {
        session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Form WHERE login = :login");
        query.setParameter("login", login);
        List<Form> loginList = query.list();
        return !loginList.isEmpty();
    }

    public int getIdByLogin(String login) {
        session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Form WHERE login = :login");
        query.setParameter("login", login);
        List<Form> personList = query.list();
        return personList.get(0).getId();
    }
}
