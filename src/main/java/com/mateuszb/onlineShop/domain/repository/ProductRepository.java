package com.mateuszb.onlineShop.domain.repository;

import com.mateuszb.onlineShop.domain.Product;

import java.util.List;

public interface ProductRepository {

	List <Product> getAllProducts();
	
	Product getProductById(String productID);
}
