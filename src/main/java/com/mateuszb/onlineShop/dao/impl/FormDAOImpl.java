package com.mateuszb.onlineShop.dao.impl;


import com.mateuszb.onlineShop.dao.FormDAO;
import com.mateuszb.onlineShop.dto.Form;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import org.hibernate.query.Query;

public class FormDAOImpl implements FormDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertForm(Form form) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(form);
        tx.commit();
        session.close();
    }

    public int getIdByLogin(String login) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM Form WHERE LOGIN = :login");
        query.setParameter("login", login);
        List<Form> personList = query.list();
        return personList.get(0).getId();
    }
}
