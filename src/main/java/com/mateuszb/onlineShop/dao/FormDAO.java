package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.dto.Form;

public interface FormDAO {
    void insertForm(Form form);
    Form getDataByLogin(String login);
}