package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.CategoryDAO;
import com.mateuszb.onlineShop.dao.LogsDAO;
import com.mateuszb.onlineShop.dao.ManufactureDAO;
import com.mateuszb.onlineShop.dao.ProductDAO;
import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.dto.Category;
import com.mateuszb.onlineShop.dto.Manufacture;
import com.mateuszb.onlineShop.dto.ProductDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private SessionFactory sessionFactory;
    private ClassPathXmlApplicationContext context;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean insertProduct(Product product, List<Product> productList) {
        context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        ManufactureDAO manufactureDAO = context.getBean(ManufactureDAO.class);
        CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
        LogsDAO logsDAO = context.getBean(LogsDAO.class);

        for (Product p : productList) {
            if (p.getProductId().equalsIgnoreCase(product.getProductId())) {
                logsDAO.insert("Produkt o takim ID już istnieje w bazie");
                return false;
            }
        }

        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(product.getName())) {
                logsDAO.insert("Produkt o takiej nazwie już istnieje w bazie");
                return false;
            }
        }

        boolean exists = false;

        for (Product p : productList) {
            if (p.getManufacturer().equalsIgnoreCase(product.getManufacturer())) {
                exists = true;
            }
        }

        Manufacture manufacture;

        Session session;
        if (!exists) {
            manufacture = new Manufacture();
            manufacture.setName(product.getManufacturer());
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(manufacture);
            tx.commit();
            session.close();
            logsDAO.insert("Nowy producent: " + manufacture.getName() + " prawidlowo dodany do bazy.");
        }

        exists = false;

        for (Product p : productList) {
            if (p.getCategory().equals(product.getCategory())) {
                exists = true;
            }
        }

        Category category;

        if (!exists) {
            category = new Category();
            category.setName(product.getCategory());
            session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(category);
            tx.commit();
            session.close();
            logsDAO.insert("Nowa kategoria: " + category.getName() + " prawidlowo dodana do bazy.");
        }

        ProductDao productDao = new ProductDao();
        productDao.setProductId(product.getProductId());
        productDao.setName(product.getName());
        productDao.setDescription(product.getDescription());
        productDao.setProduct_condition(product.getProduct_condition());
        productDao.setUnitPrice(product.getUnitPrice().intValue());
        productDao.setUnitsInStock((int) product.getUnitsInStock());
        productDao.setCategoryId(categoryDAO.getCategoryId(product.getCategory()));
        productDao.setManufacturerId(manufactureDAO.getManufactureId(product.getManufacturer()));

        return addProductUsingHibernate(productDao, logsDAO);
        //return addProductUsingSql(productDao, logsDAO);
    }

    private boolean addProductUsingHibernate(ProductDao product, LogsDAO logsDAO) {
        Transaction tx = null;
        Session session = null;
        try {
            session = this.sessionFactory.openSession();
            tx = session.beginTransaction();
            session.save(product);
            tx.commit();
            logsDAO.insert("Poprawnie dodano nowy produkt o nazwie: " + product.getName());
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            logsDAO.insert(e.getMessage());
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return true;
    }

    private boolean addProductUsingSql(ProductDao product, LogsDAO logsDAO) {
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/onlineShop?useSSL=false";
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(myUrl, "jgac", "jgac");

            String query = " insert into PRODUCT (productID, name, description, product_condition, unitPrice, unitsInStock, category_id, manufacturer_id)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, product.getProductId());
            preparedStmt.setString(2, product.getName());
            preparedStmt.setString(3, product.getDescription());
            preparedStmt.setString(4, product.getProduct_condition());
            preparedStmt.setInt(5, product.getUnitPrice());
            preparedStmt.setInt(6, product.getUnitsInStock());
            preparedStmt.setInt(7, product.getCategoryId());
            preparedStmt.setInt(8, product.getManufacturerId());

            preparedStmt.execute();
            connection.close();
            logsDAO.insert("Poprawnie dodano nowy produkt o nazwie: " + product.getName());
        } catch (Exception e) {
            logsDAO.insert(e.getMessage());
            return false;
        }
        return true;
    }

    public List<Product> getAllProducts() {
        return getAllProductsUsingHibernate();
        //return getAllProductsUsingSQL();
    }

    private List<Product> getAllProductsUsingHibernate() {
        context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        LogsDAO logsDAO = context.getBean(LogsDAO.class);
        ManufactureDAO manufactureDAO = context.getBean(ManufactureDAO.class);
        CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);

        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        List<ProductDao> productDaoList = null;
        try {
            tx = session.beginTransaction();
            productDaoList = session.createQuery("FROM ProductDao").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            logsDAO.insert(e.getMessage());
        } finally {
            session.close();
        }

        List<Product> productList = new ArrayList<Product>();

        if (productDaoList != null) {
            for (ProductDao productDao : productDaoList) {
                Product product = new Product();
                product.setId(productDao.getId());
                product.setProductId(productDao.getProductId());
                product.setName(productDao.getName());
                product.setDescription(productDao.getDescription());
                product.setProduct_condition(productDao.getProduct_condition());
                product.setUnitPrice(BigDecimal.valueOf(productDao.getUnitPrice()));
                product.setUnitsInStock(productDao.getUnitsInStock());
                product.setUnitsInOrder(productDao.getUnitsInOrder());
                product.setDiscontinued(productDao.isDiscontinued());
                product.setCategory(categoryDAO.getCategoryNameById(productDao.getCategoryId()));
                product.setManufacturer(manufactureDAO.getManufactureNameById(productDao.getManufacturerId()));
                productList.add(product);
            }
        }
        return productList;
    }

    private List<Product> getAllProductsUsingSQL() {
        context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        LogsDAO logsDAO = context.getBean(LogsDAO.class);
        ManufactureDAO manufactureDAO = context.getBean(ManufactureDAO.class);
        CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);

        List<Product> productList = new ArrayList<Product>();
        try {
            String myDriver = "com.mysql.jdbc.Driver";
            String myUrl = "jdbc:mysql://localhost:3306/onlineShop?useSSL=false";
            Class.forName(myDriver);
            Connection connection = DriverManager.getConnection(myUrl, "jgac", "jgac");

            String query = "SELECT * FROM PRODUCT";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getString("productID"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setProduct_condition(resultSet.getString("product_condition"));
                product.setUnitPrice(BigDecimal.valueOf(resultSet.getInt("unitPrice")));
                product.setUnitsInStock(resultSet.getInt("unitsInStock"));
                product.setCategory(categoryDAO.getCategoryNameById(resultSet.getInt("category_id")));
                product.setManufacturer(manufactureDAO.getManufactureNameById(resultSet.getInt("manufacturer_id")));
                productList.add(product);
            }
            statement.close();
        } catch (Exception e) {
            logsDAO.insert(e.getMessage());
        }
        return productList;
    }
}