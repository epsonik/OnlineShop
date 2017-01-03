package com.mateuszb.onlineShop.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLES")
public class Role {

    @Id
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "role_id")
    private int role_id;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }

    @Override
    public String toString() {
        return "user_id: " + user_id + " role_id: " + role_id;
    }
}