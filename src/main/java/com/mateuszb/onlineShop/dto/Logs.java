package com.mateuszb.onlineShop.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LOGS")
public class Logs {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Log nr: " + id + " - " + description;
    }
}
