package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.CategoryDAO;
import com.mateuszb.onlineShop.dao.LogsDAO;
import com.mateuszb.onlineShop.dao.ManufactureDAO;
import com.mateuszb.onlineShop.dao.ProductDAO;
import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.dto.Category;
import com.mateuszb.onlineShop.dto.Manufacture;
import com.mateuszb.onlineShop.dto.ProductDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private SessionFactory sessionFactory;
    private Session session;
    private ClassPathXmlApplicationContext context;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean insertProduct(Product product, List<Product> productList) {
        context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        ManufactureDAO manufactureDAO = context.getBean(ManufactureDAO.class);
        CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
        LogsDAO logsDAO = context.getBean(LogsDAO.class);

        for(Product p: productList) {
            if(p.getProductId().equalsIgnoreCase(product.getProductId())) {
                logsDAO.insert("Produkt o takim ID już istnieje w bazie");
                return false;
            }
        }

        for(Product p: productList) {
            if(p.getName().equalsIgnoreCase(product.getName())) {
                logsDAO.insert("Produkt o takiej nazwie już istnieje w bazie");
                return false;
            }
        }

        boolean exists = false;

        for(Product p: productList) {
            if(p.getManufacturer().equalsIgnoreCase(product.getManufacturer())) {
                exists = true;
            }
        }

        Manufacture manufacture = null;

        if(!exists){
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

        for(Product p: productList) {
            if(p.getCategory().equals(product.getCategory())) {
                exists = true;
            }
        }

        Category category = null;

        if(!exists) {
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
        productDao.setProduct_Condition(product.getProduct_Condition());
        productDao.setUnitPrice(product.getUnitPrice().intValue());
        productDao.setUnitsInStock((int) product.getUnitsInStock());
        productDao.setCategoryId(categoryDAO.getCategoryId(product.getCategory()));
        productDao.setManufacturerId(manufactureDAO.getManufactureId(product.getManufacturer()));

        session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(productDao);
        tx.commit();
        session.close();

        return true;
    }

    public List<Product> getAllProducts() {
        session = this.sessionFactory.openSession();
        List<ProductDao> productDaoList = session.createQuery("FROM ProductDao").list();
        session.close();

        context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");
        ManufactureDAO manufactureDAO = context.getBean(ManufactureDAO.class);
        List<Manufacture> manufactureList = manufactureDAO.getAllManufactures();

        CategoryDAO categoryDAO = context.getBean(CategoryDAO.class);
        List<Category> categoryList = categoryDAO.getAllCategories();

        session.close();

        List<Product> productList = new ArrayList<Product>();

        for(ProductDao productDao: productDaoList){
            Product product = new Product();
            product.setId(productDao.getId());
            product.setProductId(productDao.getProductId());
            product.setName(productDao.getName());
            product.setDescription(productDao.getDescription());
            product.setCondition(productDao.getProduct_Condition());
            product.setUnitPrice(BigDecimal.valueOf(productDao.getUnitPrice()));
            product.setUnitsInStock(productDao.getUnitsInStock());
            product.setUnitsInOrder(productDao.getUnitsInOrder());
            product.setDiscontinued(productDao.isDiscontinued());

            for(Manufacture manufacture: manufactureList) {
                if(manufacture.getId() == productDao.getManufacturerId()) {
                    product.setManufacturer(manufacture.getName());
                }
            }

            for(Category category: categoryList) {
                if(category.getId() == productDao.getCategoryId()) {
                    product.setCategory(category.getName());
                }
            }

            productList.add(product);
        }

        return productList;
    }

}
