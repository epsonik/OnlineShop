package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.ProductDAO;
import com.mateuszb.onlineShop.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void insertProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(product);
        tx.commit();
        session.close();
    }

    public List<Product> getAllProducts() {
        Session session = this.sessionFactory.openSession();
        List<Product> productList = session.createQuery("FROM Product").list();
        session.close();
        return productList;
    }
}
