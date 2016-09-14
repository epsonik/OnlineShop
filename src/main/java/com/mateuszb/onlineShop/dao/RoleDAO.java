package com.mateuszb.onlineShop.dao;

public interface RoleDAO {

    void insert(int userId);
    int getRoleId(int userId);

}
