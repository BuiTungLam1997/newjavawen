package com.example.javaweb.mapper;

import com.example.javaweb.model.NewModel;
import com.example.javaweb.model.RoleModel;
import com.example.javaweb.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {
    @Override
    public UserModel mapRow(ResultSet resultSet) {
        UserModel userModel = new UserModel();
        try {
            userModel.setId(resultSet.getLong("id"));
            userModel.setUserName(resultSet.getString("username"));
            userModel.setPassword(resultSet.getString("password"));
            userModel.setStatus(resultSet.getInt("status"));
            userModel.setFullName(resultSet.getString("fullname"));
            try {
                RoleModel role = new RoleModel();
                role.setCode(resultSet.getString("code"));
                role.setName(resultSet.getString("name"));
                userModel.setRole(role);
            } catch (Exception e) {
                System.out.println(e);
            }
            return userModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
