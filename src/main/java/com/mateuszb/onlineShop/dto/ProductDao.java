package com.mateuszb.onlineShop.dto;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Min;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PRODUCT")
public class ProductDao {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "productID")
    @Size(min=3, max=20)
    private String productId;

    @Column(name = "name")
    @Size(min=3, max=20)
    private String name;

    @Column(name = "description")
    @Size(min=3, max=200)
    private String description;

    @Column(name = "product_condition")
    @NotEmpty
    private String product_condition;

    @Column(name = "unitPrice")
    private int unitPrice;

    @Column(name = "unitsInStock")
    private int unitsInStock;

    @Column(name = "unitsInOrder")
    private int unitsInOrder;

    @Column(name = "discontinued")
    private boolean discontinued;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "manufacturer_id")
    private int manufacturerId;

    public int getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public int getUnitsInOrder() {
        return unitsInOrder;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public void setUnitsInOrder(int unitsInOrder) {
        this.unitsInOrder = unitsInOrder;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getProduct_condition() {
        return product_condition;
    }

    public void setProduct_condition(String product_condition) {
        this.product_condition = product_condition;
    }

    @Override
    public String toString() {
        return "Produkt: " + name;
    }
}
