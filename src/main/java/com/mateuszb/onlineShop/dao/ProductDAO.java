package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.domain.Product;

import java.util.List;

public interface ProductDAO {

    void insertProduct(Product product);

    List<Product> getAllProducts();

}
