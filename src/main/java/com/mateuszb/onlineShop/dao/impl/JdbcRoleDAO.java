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

    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void insert(int userId) {
        String sqlStatement = "INSERT INTO USER_ROLES " + "(user_id, role_id) VALUES (?, ?)";

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareCall(sqlStatement);
            ps.setInt(1,userId);
            ps.setInt(2,1);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try{
                    connection.close();
                } catch (SQLException e) { }
            }
        }
    }

    public int getRoleId(int userId) {
        String sqlStatement = "SELECT * FROM USER_ROLES WHERE USER_ID = ?";

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareCall(sqlStatement);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return rs.getInt("role_id");
            }

            rs.close();
            ps.close();

            return 0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try{
                    connection.close();
                } catch (SQLException e) { }
            }
        }
    }
}
