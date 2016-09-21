package com.mateuszb.onlineShop.dao;

public interface RoleDAO {

    void insert(String login);

    int getRoleId(String login);

}
