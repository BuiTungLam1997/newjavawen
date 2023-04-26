package com.example.javaweb.dao.impl;

import com.example.javaweb.dao.ICategoryDAO;
import com.example.javaweb.model.CategoryModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//load driver
            String url = "jdbc:mysql://localhost:3306/newjavaweb";
            String user = "root";
            String password = "Tiki@1eio";
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }
    @Override
    public List<CategoryModel> findAll() {
        List<CategoryModel> results = new ArrayList<>();
        String sql = "select * from category";
        //open connetion
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null) {
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    CategoryModel category = new CategoryModel();
                    category.setId(resultSet.getLong("id"));
                    category.setCode(resultSet.getString("code"));
                    category.setName(resultSet.getString("name"));
                    results.add(category);
                }
                return results;

            } catch (SQLException e) {
                return null;
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return results;
    }
}
