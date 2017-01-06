package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.dto.User;

public interface UserDAO {
    void insertNewUserToDatabase(User user);
    int getIdByLogin(String login);
    boolean checkExistingLogin(String login);
}
