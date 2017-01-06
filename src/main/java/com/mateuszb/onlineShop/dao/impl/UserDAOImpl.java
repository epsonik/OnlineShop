package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.UserDAO;
import com.mateuszb.onlineShop.dto.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;
    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertNewUserToDatabase(User user) {
        session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }

    public int getIdByLogin(String login) {
        session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE login = :login");
        query.setParameter("login", login);
        List<User> users = query.list();
        session.close();
        return users.get(0).getId();
    }

    public boolean checkExistingLogin(String login) {
        session = this.sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE login = :login");
        query.setParameter("login", login);
        List<User> loginList = query.list();
        return !loginList.isEmpty();
    }
}
