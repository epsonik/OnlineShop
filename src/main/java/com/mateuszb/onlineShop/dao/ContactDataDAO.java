package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.dto.ContactData;

public interface ContactDataDAO {
    void insertContactData(ContactData contactData);
    ContactData getContactDataByUserID(int user_id);
}
