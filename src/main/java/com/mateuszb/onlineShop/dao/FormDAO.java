package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.dto.Form;

public interface FormDAO {

    void insertForm(Form form);

    boolean checkEmail(String email);

    boolean checkLogin(String login);

    int getIdByLogin(String login);

}
