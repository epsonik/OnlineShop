package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.ContactDataDAO;
import com.mateuszb.onlineShop.dto.ContactData;
import com.mateuszb.onlineShop.dto.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ContactDataDAOImpl implements ContactDataDAO {

    private Session session;
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertContactData(ContactData contactData) {
        session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(contactData);
        tx.commit();
        session.close();
    }

    public ContactData getContactDataByUserID(int user_id) {
        session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM ContactData WHERE user_id = :user_id");
        query.setParameter("user_id", user_id);
        List<ContactData> loginList = query.list();
        return loginList.get(0);
    }
}
