package com.mateuszb.onlineShop.dao.impl;


import com.mateuszb.onlineShop.dao.FormDAO;
import com.mateuszb.onlineShop.dto.Form;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcFormDAO implements FormDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Form form) {

        String sqlStatement = "INSERT INTO USER_TABLE " + "(firstName, lastName, email, login, password) VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareCall(sqlStatement);
            ps.setString(1, form.getFirstName());
            ps.setString(2, form.getLastName());
            ps.setString(3, form.getEmail());
            ps.setString(4, form.getLogin());
            ps.setString(5, form.getPassword());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public Form findById(int id) {

        String sqlStatement = "SELECT * FROM USER_TABLE WHERE ID = ?";

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareCall(sqlStatement);
            ps.setInt(1, id);
            Form form = null;
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                form = new Form(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),
                        rs.getString("login"), rs.getString("password"));
            }

            rs.close();
            ps.close();

            return form;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    public Form findByLogin(String login) {
        String sqlStatement = "SELECT * FROM USER_TABLE WHERE LOGIN = ?";

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareCall(sqlStatement);
            ps.setString(1, login);
            Form form = null;
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                form = new Form(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"),
                        rs.getString("login"), rs.getString("password"));
            }

            rs.close();
            ps.close();

            return form;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}
