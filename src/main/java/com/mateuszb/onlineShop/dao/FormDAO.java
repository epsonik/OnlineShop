package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.dto.Form;

public interface FormDAO {

    void insert(Form form);

    Form findById(int id);

    Form findByLogin(String login);
}
