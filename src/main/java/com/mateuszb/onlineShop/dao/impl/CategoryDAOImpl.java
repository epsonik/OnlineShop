package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.CategoryDAO;
import com.mateuszb.onlineShop.dto.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    private SessionFactory sessionFactory;
    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Category> getAllCategories() {
        session = this.sessionFactory.openSession();
        List<Category> categoryList = session.createQuery("from Category").list();
        session.close();
        return categoryList;
    }

    public int getCategoryId(String name) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Category where name = :name");
        query.setParameter("name", name);
        List<Category> categoryList = query.list();
        session.close();
        return categoryList.get(0).getId();
    }
}
