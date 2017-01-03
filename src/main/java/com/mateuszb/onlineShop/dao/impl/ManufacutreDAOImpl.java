package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.ManufactureDAO;
import com.mateuszb.onlineShop.dto.Manufacture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ManufacutreDAOImpl implements ManufactureDAO {

    private SessionFactory sessionFactory;
    private Session session;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Manufacture> getAllManufactures() {
        session = this.sessionFactory.openSession();
        List<Manufacture> manufactureList = session.createQuery("from Manufacture").list();
        session.close();
        return manufactureList;
    }

    public int getManufactureId(String name) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Manufacture where name = :name");
        query.setParameter("name", name);
        List<Manufacture> manufactureList = query.list();
        return manufactureList.get(0).getId();
    }

    public String getManufactureNameById(int id) {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("from Manufacture where id = :id");
        query.setParameter("id", id);
        List<Manufacture> manufactureList = query.list();
        return manufactureList.get(0).getName();
    }
}
