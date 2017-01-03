package com.mateuszb.onlineShop.dao;

import com.mateuszb.onlineShop.dto.Manufacture;

import java.util.List;

public interface ManufactureDAO {

    List<Manufacture> getAllManufactures();
    int getManufactureId(String name);
    String getManufactureNameById(int id);
}
