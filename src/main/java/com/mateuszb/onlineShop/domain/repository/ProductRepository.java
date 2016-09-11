package com.mateuszb.onlineShop.domain.repository;

import com.mateuszb.onlineShop.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;



public interface ProductRepository {

	List <Product> getAllProducts();

	Product getProductById(String productID);

	List<Product> getProductsByCategory(String category);

	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	public void addProduct(Product product);
}