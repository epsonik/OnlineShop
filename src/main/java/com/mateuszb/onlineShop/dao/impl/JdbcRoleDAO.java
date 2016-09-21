package com.mateuszb.onlineShop.dao.impl;

import com.mateuszb.onlineShop.dao.RoleDAO;
import com.mateuszb.onlineShop.dto.Form;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcRoleDAO implements RoleDAO {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(String login) {
        String sqlStatement = "INSERT INTO USER_ROLES " + "(login, role_id) VALUES (?, ?)";

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareCall(sqlStatement);
            ps.setString(1, login);
            ps.setInt(2, 1);
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

    public int getRoleId(String login) {
        String sqlStatement = "SELECT * FROM USER_ROLES WHERE login = ?";

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareCall(sqlStatement);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("role_id");
            }

            rs.close();
            ps.close();

            return 0;
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
