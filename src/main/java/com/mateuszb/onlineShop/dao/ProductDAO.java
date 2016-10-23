package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.domain.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getAllProducts();
    boolean insertProduct(Product product, List<Product> productList);
}
