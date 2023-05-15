package com.example.javaweb.dao.impl;

import com.example.javaweb.dao.IUserDao;
import com.example.javaweb.mapper.NewMapper;
import com.example.javaweb.mapper.UserMapper;
import com.example.javaweb.model.NewModel;
import com.example.javaweb.model.UserModel;

import java.util.List;

public class UserDao extends AbstractDAO<UserModel> implements IUserDao {

    @Override
    public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
        StringBuilder sql = new StringBuilder("select * from user as u ");
        sql.append(" inner join role as r on r.id = u.roleid");
        sql.append(" where username = ? and password = ? and status = ?");
        List<UserModel> userModels = query(sql.toString(), new UserMapper(), userName,password,status);
        return userModels.isEmpty() ? null : userModels.get(0);
    }
}
