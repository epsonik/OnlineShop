package com.mateuszb.onlineShop.service.impl;

import com.mateuszb.onlineShop.domain.Product;
import com.mateuszb.onlineShop.domain.repository.ProductRepository;
import com.mateuszb.onlineShop.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository = null;

	public ProductServiceImpl(){
		ApplicationContext context = new ClassPathXmlApplicationContext("/Spring-module-product.xml");
		productRepository = (ProductRepository) context.getBean("productDAO");
	}

	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}
	
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}

	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductsByFilter(filterParams);
	}

	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

}
