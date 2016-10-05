package com.mateuszb.onlineShop.domain.repository.impl;

import com.mateuszb.onlineShop.dao.ProductDAO;
import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.domain.repository.ProductRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class InMemoryProductRepository implements ProductRepository {

	private List<Product> listOfProducts = new ArrayList<Product>();

	public List<Product> getAllProducts() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Spring-Datasource.xml");

        ProductDAO productDAO = context.getBean(ProductDAO.class);
        listOfProducts = productDAO.getAllProducts();

        return listOfProducts;
	}

	public Product getProductById(String productId) {
		return null;
	}

	public List<Product> getProductsByCategory(String category) {
		List<Product> productsByCategory = new ArrayList<Product>();

		listOfProducts = getAllProducts();

		for(Product product: listOfProducts) {
			if(category.equalsIgnoreCase(product.getCategory())){
				productsByCategory.add(product);
			}
		}

		return productsByCategory;
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();

		Set<String> criterias = filterParams.keySet();

		listOfProducts = getAllProducts();

		if(criterias.contains("brand")) {
			for(String brandName: filterParams.get("brand")) {
				for(Product product: listOfProducts) {
					if(brandName.equalsIgnoreCase(product.getManufacturer())){
						productsByBrand.add(product);
					}
				}
			}
		}

		if(criterias.contains("category")) {
			for(String category: filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}

		productsByCategory.retainAll(productsByBrand);

		return productsByCategory;
	}

	public void addProduct(Product product) {

	}
}
