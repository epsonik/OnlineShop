package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.dto.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getAllCategories();
    int getCategoryId(String name);
}
